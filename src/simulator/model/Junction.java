package simulator.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Junction extends SimulatedObject {

    private final List<Road> inRoads;                       //Lista carreteras entrantes.
    private final Map<Junction, Road> outRoads;             //Mapa para buscar.
    private final List<List<Vehicle>> queuesL;              //Lista de las colas
    private final Map<Road, LinkedList<Vehicle>> queuesM;   //Map para buscar.
    private int greenIndex;
    private int remainingUntilLightSwitch;
    private final LightSwitchingStrategy lsStrategy;
    private final DequeuingStrategy dqStrategy;

    //Not functional until next version.
    private final int x;
    private final int y;
    //--------------------------------//
    Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int x, int y) {
        super(id);
        if (lsStrategy == null) throw new IllegalArgumentException("LightSwitchingStrategy not valid");
        if (dqStrategy == null) throw new IllegalArgumentException("DequeuingStrategy not valid");

        this.lsStrategy = lsStrategy;
        this.dqStrategy = dqStrategy;
        this.x = x;
        this.y = y;
        this.greenIndex = -1;
        this.remainingUntilLightSwitch = 0;
        this.inRoads = new ArrayList<>();
        this.outRoads = new HashMap<>();
        this.queuesL = new ArrayList<>();
        this.queuesM = new HashMap<>();
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getGreenLightIndex(){
        return greenIndex;
    }

    public List<Road> getInRoads(){
        return inRoads;
    }

    public JSONObject getReport(){
        return this.report();
    }

    public void addIncomingRoad(Road r) {
        if (r.getDest().getId().equals(this.getId()) && !inRoads.contains(r)) {
            LinkedList<Vehicle> list = new LinkedList<>();
            queuesL.add(list);
            inRoads.add(r);
            queuesM.put(r, list);
        } else {
            throw new IllegalArgumentException("invalid  incoming road");
        }
    }

    public void addOutgoingRoad(Road r) {
        if (!r.getDest().getId().equals(this.getId()) && !outRoads.containsKey(r.getDest())) {
            outRoads.put(r.getDest(), r);
        } else {
            throw new IllegalArgumentException("invalid  incoming road");
        }
    }

    void enter(Vehicle v) {
        queuesM.get(v.getRoad()).addLast(v);
    }

    Road roadTo(Junction j) {
        return outRoads.get(j);
    }

    @Override
    void advance(int time) {

        if(greenIndex != -1) {
            //Sacamos la lista de vehículos a mover de la cola a la que apunta greenIndex.
            List<Vehicle> qL = dqStrategy.dequeue(queuesL.get(greenIndex));
            for (Vehicle v : qL) {
                queuesM.get(v.getRoad()).remove(v);
                queuesL.get(greenIndex).remove(v);
                v.moveToNextRoad();
            }
        }


        int i = lsStrategy.chooseNextGreen(inRoads, queuesL, greenIndex, remainingUntilLightSwitch, time);
        if (i != greenIndex) {
            remainingUntilLightSwitch = time;
        }
        greenIndex = i;
    }

    @Override
    public JSONObject report() {
        JSONObject object = new JSONObject();
        object.put("id", this.getId());

        JSONArray coor = new JSONArray();
        object.put("coor", coor);
        coor.put(x);
        coor.put(y);

        if (greenIndex == -1) {
            object.put("green", "none");
        } else
            object.put("green", inRoads.get(greenIndex).getId());

        JSONArray jsonList = new JSONArray();
        object.put("queues", jsonList);


        /*
         * Para cada Road:
         * "road", "r1"
         * "vehicles", ["v1", "v2", ...]
         * */
        for (Road r : inRoads) {
            JSONObject o = new JSONObject();
            jsonList.put(o);
            o.put("road", r.getId());

            JSONArray vArray = new JSONArray();
            o.put("vehicles", vArray);
            for (Vehicle v : queuesM.get(r)) {
                vArray.put(v.getId());
            }
        }
        return object;
    }
}
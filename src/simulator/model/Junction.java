package simulator.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Junction extends SimulatedObject {

    private List<Road> inRoads;         //Lista carreteras entrantes.
    private Map<Junction, Road> outRoads; //
    private List<List<Vehicle>> queuesL;        //List for adding and iterating
    private Map<Road, List<Vehicle>> queuesM;   //Map for searching
    private int greenIndex;
    private int remainingUntilLightSwitch;
    private LightSwitchingStrategy lsStrategy;
    private DequeuingStrategy dqStrategy;
    //Not functional until next version.
    private int x;
    private int y;

    Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int x, int y) {
        super(id);
        if (lsStrategy == null) //throw;
            if (dqStrategy == null) //throw;
                if (x < 0) ;
        if (y < 0) ;
        this.lsStrategy = lsStrategy;
        this.dqStrategy = dqStrategy;
        this.x = x;
        this.y = y;
        this.greenIndex = -1;
        this.remainingUntilLightSwitch = 0;

    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    public JSONObject getReport() {
        return report();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void addIncomingRoad(Road r) {
        if (r == inRoads) {//TODO acomodar sentencia
            LinkedList<Vehicle> list = new LinkedList<>();
            ((LinkedList) queuesL).addLast(list);
            inRoads.add(r);
            queuesM.put(r, list);
        } else {
            throw new IllegalArgumentException("invalid  incoming road");
        }
    }

    public void addOutgoingRoad(Road r) {
        if (r.getDest() != this || r == outRoads) {
            outRoads.put(r.getDest(), r);
        } else {
            throw new IllegalArgumentException("invalid  incoming road");
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    void enter(Vehicle v) {
        queuesM.get(v.getRoad()).add(v);
    }

    @SuppressWarnings("unlikely-arg-type")
    Road roadTo(Junction j) {
        queuesM.get(j);
        return outRoads.get(j);
    }

    @Override
    void advance(int time) {
        List<Vehicle> qL = dqStrategy.dequeue(queuesL.get(greenIndex));
        for (Vehicle v : qL) {
            queuesM.get(v.getRoad()).remove(v);
            v.getRoad().getVehicles().remove(v);
            v.moveToNextRoad();
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
package simulator.model;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class Junction extends SimulatedObject{

    private List<Road> inRoads;
    private Map<Junction, Road> outRoads;
    private List<List<Vehicle>> queuesL;        //List for adding and iterating
    private Map<Road, List<Vehicle>> queuesM;   //Map for searching
    private int greenIndex;
    private int remainingUntilLightSwitch;
    private LightSwitchingStrategy lStrategy;
    private DequeuingStrategy dStrategy;
    //Not functional until next version.
    private int x;
    private int y;

    Junction(String id) {
        super(id);
    }
    int getX(){return x;}
    int getY(){return y;}

    public void addIncomingRoad(Road r){}

    public void addOutgoingRoad(Road r){}

    void enter(Vehicle v){}
    Road roadTo(Junction j){}

    @Override
    void advance(int time) {

    }

    @Override
    public JSONObject report() {
        return null;
    }
}

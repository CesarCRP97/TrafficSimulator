package simulator.model;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class Junction extends SimulatedObject{

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
    
    Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int x, int y){
        super(id);
        if(lsStrategy == null) //throw;
        if(dqStrategy == null) //throw;
        if(x < 0);
        if(y < 0);
        this.lsStrategy = lsStrategy;
        this.dqStrategy = dqStrategy;
        this.x = x;
        this.y = y;
        this.greenIndex = -1;
        this.remainingUntilLightSwitch = 0;
        
    }
    int getX(){return x;}
    int getY(){return y;}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void addIncomingRoad(Road r){
        LinkedList<Vehicle> list = new LinkedList<>();
        ((LinkedList)queuesL).addLast(list);
        inRoads.add(r);
        queuesM.put(r, list);
    }

    public void addOutgoingRoad(Road r){
		outRoads.put(r.getDest(),r);  
		if (r.getDest()!=this || r==outRoads);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	void enter(Vehicle v){
    	queuesM.get(v.getRoad()).add(v);
    }
    
    @SuppressWarnings("unlikely-arg-type")
	Road roadTo(Junction j){
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
        return null;
    }
}
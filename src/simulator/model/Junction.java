package simulator.model;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

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
	private Junction j; //estination road junstion
    
    Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int x, int y){
        super(id);
    }
    int getX(){return x;}
    int getY(){return y;}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void addIncomingRoad(Road r){
    	inRoads.add(r);
    	//crear una cola de 
    	((LinkedList) inRoads).addLast(r);
    	queuesM.put(r,(List<Vehicle>) r);
    	//compara si el cruse actual es igual al cruce destino, si no lanzar excepcion
    }

    public void addOutgoingRoad(Road r){
		outRoads.put(j,r);  
		if (j!=this || r==outRoads);  /*Tienes que comprobar que ninguna otra
    	carretera va desde this al cruce j y, que la carretera r, es realmente una carretera
    	saliente al cruce actual. En otro caso debes lanzar una excepción*/
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	void enter(Vehicle v){
    	((LinkedList) inRoads).addLast(v);
    }
    
    Road roadTo(Junction j){
		return (Road)outRoads;
	}

    @Override
    void advance(int time) {
    }

    @Override
    public JSONObject report() {
        return null;
    }
}
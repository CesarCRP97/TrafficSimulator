package simulator.model;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class RoadMap {
	protected List<Junction> junctions;
	protected List<Road> roads;
	protected List<Vehicle> vehicles;
	protected Map<String,Junction> junctionsM;
	protected Map<String,Road> roadsM;
	protected Map<String,Vehicle> vehiclesM;
	
	protected RoadMap(){}
	
	void addJunction(Junction j) {
		if(junctionsM.get(j.getId()) == null){
			junctions.add(j);
			junctionsM.put(j.getId(), j);
		}
		
	}

	void addRoad(Road r){
		if((junctionsM.get(r.getDest().getId()) == null || junctionsM.get(r.getSrc().getId()) == null) || roadsM.get(r.getId()) != null) { //throw
			roads.add(r);
			roadsM.put(r.getId(), r);
		}
		else {
    		throw new IllegalArgumentException ("the new road is invalid");
		}
	}
	
	void addVehicle(Vehicle v) {
		if(vehiclesM.get(v.getId()) != null);
		if(!validItinerary(v)) {// throw;
			vehicles.add(v);
			vehiclesM.put(v.getId(), v);
		}
		else {
    		throw new IllegalArgumentException ("Invalid Vehicle Value");
		}
	}

	public Junction getJunction(String id){
		return junctionsM.get(id);
	}

	public Road getRoad(String id){
		return roadsM.get(id);
	}

	public Vehicle getVehicle(String id){
		return vehiclesM.get(id);
	}

	public List<Junction> getJunctions(){
		return junctions;
	}


	public List<Road> getRoads(){
		return roads;
	}


	public List<Vehicle> getVehicles(){
		return vehicles;
	}

	void reset(){
		junctions.clear();
		roads.clear();
		vehicles.clear();

		junctionsM.clear();
		roadsM.clear();
		vehiclesM.clear();
	}

	public JSONObject report(){
        String jsonString = "{"
                + "\"junctions\" :" + ((RoadMap) getJunctions()).getJSONVList()
                + ", \"road\" :" + ((RoadMap) getRoads()).getJSONVList()
                + ", \"vehicles\" :" + ((RoadMap) getVehicles()).getJSONVList()
                + "}";
        return new JSONObject(jsonString);
		return null;
	}
	private boolean validItinerary(Vehicle v){
		for(Junction j : v.getItinerary()){
			if(junctionsM.get(j.getId()) == null) return false; 		//Si el Junction no existe ya devuelve falso.
		}
		for(int i = 0; i < v.getItinerary().size() - 1; i++){

		}
		return true;	//TODO
	}

	//TODO Add methods to analize if valid.
	private boolean validRoad(Road r){
		return true;
	}
	
	String getJSONVList(){
        String list = "[";
        for(Vehicle v : vehicles){
            list = list + "\"" + v.getId() + "\",";
        }
        list += "]";
        return list;
    }

}

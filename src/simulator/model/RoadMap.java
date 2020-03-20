package simulator.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class RoadMap {
	private static final Road Road = null;
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
		else{
			throw new IllegalArgumentException("Junction already existing");
		}
		
	}
	

	void addRoad(Road r){
		if(validRoad(r)) {
			roads.add(r);
			roadsM.put(r.getId(), r);
		}
		else {
    		throw new IllegalArgumentException ("the new road is invalid");
		}
	}
	
	
	
	void addVehicle(Vehicle v) {
		if(vehiclesM.get(v.getId()) != null){
			throw new IllegalArgumentException("Already existing Vehicle");
		}
		if(validItinerary(v)) {// throw;
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
        return new JSONObject(jsonString);e
		return null;
	}
	private boolean validItinerary(Vehicle v){
		for(Junction j : v.getItinerary()){
			if(junctionsM.get(j.getId()) == null) return false; 		//Si el Junction no existe ya devuelve falso.
		}

		JSONArray  rArray = new JSONArray();
		o.put("roads", rArray);
		for(Road r : getRoads()){
			rArray.put(r.getReport());
		}

		JSONArray vArray = new JSONArray();
		o.put("vehicles", vArray);
		for(Vehicle v : getVehicles()){
			vArray.put(v.getReport());
		}
		return o;
	}
	private boolean validItinerary(Vehicle v){
		List<Junction> itinerary = v.getItinerary();
		for(int i = 0 ; i < itinerary.size()-1 ; i++){
			if(itinerary.get(i).roadTo(itinerary.get(i+1)) == null) return false;
		}
		return true;	//TODO
	}

	//TODO Add methods to analize if valid.
	private boolean validRoad(Road r){
		return junctionsM.get(r.getDest().getId()) != null && junctionsM.get(r.getSrc().getId()) != null && roadsM.get(r.getId()) == null;
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

package simulator.model;

import java.util.List;
import java.util.Map;

public class RoadMap {
	protected List<Junction> junctions;
	protected List<Road> roads;
	protected List<Vehicle> vehicles;
	protected Map<String,Junction> junctionsM;
	protected Map<String,Road> roadsM;
	protected Map<String,Vehicle> vehiclesM;
	
	RoadMap() {
	}
	
	void addJunction(Junction j) {
		if(junctionsM.get(j.getId()) != null); //TODO Exception

		junctions.add(j);
		junctionsM.put(j.getId(), j);
	}

	void addRoad(Road r){
		roads.add(r);
		roadsM.put(r.getId(), r);
	}
	void addVehicle(Vehicle v) {
<<<<<<< HEAD
		vehicles.add(v);
		vehiclesM.put(v.getId(), v);

	}
}
=======
		ve.add(v);
		vehiclesMap.entrySet().forEach(null);
		/*(i) no existe ning�n otro veh�culo con el mismo identificador; y (ii)
		el itinerario es v�lido, es decir, existen carreteras que conecten los cruces consecutivos
		de su itinerario. En caso de que no se cumplan (i) y (ii), el m�todo debe lanzar una
		excepci�n.*/
	}
	
	public Junction getJunction(String id) {
		return null;
	}
	
	public Road getRoad(String id) {
		return null;
	}
	
	public Vehicle getVehicle(String id) {
		return null;
	}
	
	public List<Junction> getJunctions() {
		return junct;
	}
	
	public List<Road> getRoads() {
		return roads;
	}
	
	public List<Vehicle> getVehicles() {
		return ve;
	}

	void reset() {}
	
    @Override
    public JSONObject report() {
        return null;
        //TODO
    }
}
>>>>>>> 0b81b8519c271c95b64a589558673739c2afe2d1

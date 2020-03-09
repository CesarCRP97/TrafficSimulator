package simulator.model;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class RoadMap {
	protected List<Junction> junct;
	protected List<Road> roads;
	protected List<Vehicle> ve;
	protected Map<String,Junction> junctionMap;
	protected Map<String,Road> roadsMap;
	protected Map<String,Vehicle> vehiclesMap;
	
	RoadMap() {
	}
	
	void addJunction(Junction j) {
		junct.add(j);
		junctionMap.entrySet().forEach(null);
	}
	
	void addVehicle(Vehicle v) {
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

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
		/*(i) no existe ningún otro vehículo con el mismo identificador; y (ii)
		el itinerario es válido, es decir, existen carreteras que conecten los cruces consecutivos
		de su itinerario. En caso de que no se cumplan (i) y (ii), el método debe lanzar una
		excepción.*/

	}
}

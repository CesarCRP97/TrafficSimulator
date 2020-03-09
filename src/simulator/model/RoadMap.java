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
		vehicles.add(v);
		vehiclesM.put(v.getId(), v);

	}
}
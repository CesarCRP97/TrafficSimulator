package simulator.model;

import java.util.List;

public class NewVehicleEvent extends Event {

	private String id;

	public NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<String> itinerary) {
		super(time);
		this.id=id;
			// ...
	}

    @Override
    void execute(simulator.model.RoadMap map) {
    	Vehicle v = null;
    	map.addVehicle(v) ; //se supone que este metodo crea el vehicluo para luego agregarlo al mapa
    	map.vehiclesM.put(id, v).moveToNextRoad();
    }
}
package simulator.model;

public class NewCityRoadEvent extends NewRoadEvent {
	
	private String id;
	
	public NewCityRoadEvent(int time, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed, Weather weather) {
    	super(time, destJunc, destJunc, destJunc, maxSpeed, maxSpeed, maxSpeed, weather);
    	this.id = id;
    	// ...
        //TODO
    }
    @Override
    void execute(simulator.model.RoadMap map) {
    	Road r =null;
    	map.addRoad(r);//se supone que este metodo crea el road para luego agregarlo al mapa
    	map.roadsM.put(id, r);
    	//TODO
    }
}

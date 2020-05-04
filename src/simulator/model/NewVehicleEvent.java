 package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class NewVehicleEvent extends Event {
    final List<String> itinerary;
    private final String id;
    private final int maxSpeed;
    private final int contClass;

    public NewVehicleEvent(int time, String id, int maxSpeed, int
            contClass, List<String> itinerary) {
        super(time);
        this.id = id;
        this.maxSpeed = maxSpeed;
        this.contClass = contClass;
        this.itinerary = itinerary;
    }

    @Override
    void execute(simulator.model.RoadMap map) {
        try {
            Vehicle v = new Vehicle(id, maxSpeed, contClass, stringListToJunctionList(map));
            map.addVehicle(v);
            v.moveToNextRoad();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private List<Junction> stringListToJunctionList(RoadMap map) {
        List<Junction> junctions = new ArrayList<>();
        for (String j : itinerary) {
            junctions.add(map.getJunction(j));
        }
        return junctions;

    }
    
    @Override
    public String toString() {
    return "New Vehicle '"+id+"'";
    }
}
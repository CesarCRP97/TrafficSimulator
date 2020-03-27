package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class NewVehicleEvent extends Event {
    List<String> itinerary;
    private String id;
    private int maxSpeed;
    private int contClass;

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
        Vehicle v = null;
        try {
            v = new Vehicle(id, maxSpeed, contClass, stringListToJunctionList(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.addVehicle(v);
        v.moveToNextRoad();
    }


    private List<Junction> stringListToJunctionList(RoadMap map) {
        List<Junction> junctions = new ArrayList<>();
        for (String j : itinerary) {
            junctions.add(map.getJunction(j));
        }
        return junctions;

    }
}
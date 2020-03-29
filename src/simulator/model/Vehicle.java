package simulator.model;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class Vehicle extends SimulatedObject {
    private final List<Junction> itinerary;
    private final int maxSpeed;
    private int speed;
    private VehicleStatus status;
    private Road road;
    private int location; //Posicion en la carretera.
    private int contClass;
    private int totalCont;
    private int totalTravel;

    private int lastJunction;

    protected Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) {
        super(id);
        this.itinerary = Collections.unmodifiableList(itinerary);
        this.maxSpeed = maxSpeed;
        if (contClass >= 0 || contClass <= 10) {
            //throw new;
            this.contClass = contClass;
            this.totalCont = 0;
            this.totalTravel = 0;
            this.status = VehicleStatus.PENDING;
            this.lastJunction = -1;
        } else {
            throw new IllegalArgumentException("invalid value of " + contClass);
        }
    }

    //Getters
    int getLocation() {
        return location;
    }

    int getTotalTravel() {
        return totalTravel;
    }

    int getSpeed() {
        return speed;
    }

    //Setters
    void setSpeed(int s) {
        if (s < 0) {
            throw new IllegalArgumentException("invalid speed value, must be positive");
        } else {
            this.speed = Math.min(s, maxSpeed);
        }
    }

    int getContClass() {
        return contClass;
    }

    void setContClass(int c) {
        if (0 <= c && c <= 10) {
            this.contClass = c;
        } else {
            throw new IllegalArgumentException("invalid road value, must be between 0 and 10");
        }
    }

    int getTotalCont() {
        return totalCont;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public List<Junction> getItinerary() {
        return itinerary;
    }

    public Road getRoad() {
        return road;
    }

    void setRoad(Road r) {
        road = r;
    }

    public JSONObject getReport() {
        return report();
    }
    //Methods
    //Adds the Vehicle to the next Road depending on its itinerary.
    void moveToNextRoad() {
        if (status == VehicleStatus.PENDING || status == VehicleStatus.WAITING) {

            if (status == VehicleStatus.PENDING) {
                itinerary.get(0).roadTo(itinerary.get(1)).enter(this);
                status = VehicleStatus.TRAVELING;
            }
            else if (lastJunction < itinerary.size() - 1) {
                //Para eliminarlo de la carretera anterior => Sale de la carretera y pone location a 0.
                this.getRoad().exit(this);
                this.location = 0;
                itinerary.get(lastJunction).roadTo(itinerary.get(lastJunction + 1)).enter(this);
                status = VehicleStatus.TRAVELING;

            }
            else if (lastJunction == itinerary.size() - 1) {
                status = VehicleStatus.ARRIVED;
                speed = 0;
            }
            lastJunction++;
        }
        else {
            throw new IllegalArgumentException("Vehicle not PENDING or WAITING");

        }
    }

    @Override
    void advance(int time) {
        int newLocation = Math.min((location + speed), road.getLength());
        int c = (newLocation - location) * contClass;   // l = (newLocation - location); f = contClass;
        totalTravel += (newLocation - location);
        totalCont += c;

        location = newLocation;
        road.addContamination(c);

        if (location >= road.getLength()) {
            road.getDest().enter(this);
            status = VehicleStatus.WAITING;
            speed = 0;
        }
    }

    @Override
    public JSONObject report() {
        JSONObject o = new JSONObject();

        o.put("id", getId());
        o.put("speed", getSpeed());
        o.put("distance", getTotalTravel());
        o.put("co2", getTotalCont());
        o.put("class", getContClass());
        o.put("status", getStatus());
        if (status != VehicleStatus.PENDING && status != VehicleStatus.ARRIVED) {
            o.put("road", getRoad().getId());
            o.put("location", getLocation());
        }

        return o;
    }
}


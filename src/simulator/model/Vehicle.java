package simulator.model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vehicle extends SimulatedObject{

    private String id;
    private List<Junction> itinerary;
    private int maxSpeed;
    private int speed;
    private VehicleStatus status;
    private Road road;
    private int location;       //Posicion en la carretera.
    private int contClass;
    private int totalCont;
    private int totalTravel;

    private int lastJunction;

    protected Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary ) throws Exception {
        super(id);
        //TODO complete exceptions
        this.itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
        this.maxSpeed = maxSpeed;
        if(contClass < 0 || contClass > 10)
            //throw new;
        this.contClass = contClass;
        this.totalCont = 0;
        this.totalTravel = 0;
        this.status = VehicleStatus.PENDING;

        this.lastJunction = -1;
    }

    //Getters
    int getLocation(){
        return location; 
    }

    int getTotalTravel(){
        return totalTravel;
    }
    int getSpeed(){
        return speed;
    }

    int getContClass(){
        return contClass;
    }

    int getTotalCont(){
        return totalCont;
    }

    public VehicleStatus getStatus(){
        return status;
    }

    public List<Junction> getItinerary() {
        return itinerary;
    }

    public Road getRoad(){
        return road;
    }

    //Setters
    void setSpeed(int s){
        this.speed = Math.min(s, maxSpeed);
    }

    void setContClass(int c){
        this.contClass = c;
    }

    void setRoad(Road r){
        road = r;
    }


    //Methods

    //Adds the Vehicle to the next Road depending on its itinerary.
    void moveToNextRoad(){
        if(status == VehicleStatus.PENDING) {
            itinerary.get(0).roadTo(itinerary.get(1)).enter(this);
            status = VehicleStatus.TRAVELING;
        }
        else if (lastJunction < itinerary.size() - 1){
            itinerary.get(lastJunction).roadTo(itinerary.get(lastJunction + 1)).enter(this);
            status = VehicleStatus.TRAVELING;
        }
        else if (lastJunction == itinerary.size() - 1){
            status = VehicleStatus.ARRIVED;
            speed = 0;
        }
        lastJunction++;
    }

    @Override
    void advance(int time) {
        int newLocation = Math.min((location + speed * time), road.getLength());
        int c = (newLocation - location) * contClass;   // l = (newLocation - location); f = contClass;

        totalCont += c;
        road.addContamination(c);
        location = newLocation;

        if(location == road.getLength()) {
            road.getDest().enter(this);
            status = VehicleStatus.WAITING;
            speed = 0;
        }
    }

    @Override
    public JSONObject report() {
    	String jsonString = "{"
                + " \"id\" : " + this.getId()
                + ", \"speed\" : " + getSpeed()
                + ", \"distance\" :" + getTotalTravel()
                + ", \"co2\" :" + getTotalCont()
                + ", \"class\" :" + getContClass()
                + ", \"status\" :" + getStatus()
                + ", \"road\" :" + getRoad().getId()
                + ", \"location\" :" + getLocation()
                + "}";
        return new JSONObject(jsonString);
    }
//    static String jsonString = "{ \"a\": 1234, \"b\": 2e-10, \"c\": \"Hola!\", \"d\": [1,2,3], \"e\": { \"k\" : 123,  \"h\" : \"Helloooo!\", \"f\": 23.3e-10 }}";


    //Additional Methods
    public Junction getNextJunction(){
        return(lastJunction == itinerary.size() - 1) ? null : itinerary.get(lastJunction + 1);
    }

    
    
}


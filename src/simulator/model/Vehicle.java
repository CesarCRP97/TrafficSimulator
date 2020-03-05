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

    protected Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary ){ 
        super(id);
        //TODO complete exceptions
        this.itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
        this.maxSpeed = maxSpeed;
        if(contClass < 0 || contClass > 10)
            throw new
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

    int getSpeed(){
        return speed;
    }

    int getContClass(){
        return contClass;
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
        }
    }

    @Override
    public JSONObject report() {
    	
        return null;
    }

    //Additional Methods
    public Junction getNextJunction(){
        return(lastJunction == itinerary.size() - 1) ? null : itinerary.get(lastJunction + 1);
    }
    
    
}


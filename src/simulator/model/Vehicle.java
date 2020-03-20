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
    private int location; //Posicion en la carretera.
    private int contClass;
    private int totalCont;
    private int totalTravel;

    private int lastJunction;
   
    protected Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary ) throws Exception {
        super(id);
        //TODO complete exceptions
        this.itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
        this.maxSpeed = maxSpeed;
        if(contClass < 0 || contClass > 10) {
            //throw new;
        	this.contClass = contClass;
        	this.totalCont = 0;
        	this.totalTravel = 0;
        	this.status = VehicleStatus.PENDING;
        	this.lastJunction = - 1;
        }
        else {
        	throw new IllegalArgumentException ("invalid value of "+ contClass);
        }
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

    public JSONObject getReport(){
        return report();
    }
    //Setters
    void setSpeed(int s){
    	if (s < 0) {
    		throw new IllegalArgumentException ("invalid speed value, must be positive" );
    	}
    	else {
    		this.speed = Math.min(s, maxSpeed);
    	}
    }

    void setContClass(int c){
    	if ( 0 < c && c > 10) {
        this.contClass = c;
    	}
    	else {
    		throw new IllegalArgumentException ("invalid road value, must be between 0 and 10" );
    	}
    }

    void setRoad(Road r){
        road = r;
    }
    //Methods

    //Adds the Vehicle to the next Road depending on its itinerary.
    void moveToNextRoad(){
    	if (status != VehicleStatus.PENDING || status != VehicleStatus.WAITING) {
    		throw new IllegalArgumentException ("invalid road value, must be between 0 and 10" );
    	}	
    	else {
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
    	JSONObject o = new JSONObject();

    	o.put("id", getId());
    	o.put("distance", getTotalTravel());
    	o.put("co2", getTotalCont());
    	o.put("class", getContClass());
    	o.put("status", getStatus());
    	if(status != VehicleStatus.PENDING && status != VehicleStatus.ARRIVED) {
            o.put("road", getRoad().getId());
            o.put("location", getLocation());
        }

    	return o;
    }

    //Additional Methods

    public Junction getNextJunction(){
        return(lastJunction == itinerary.size() - 1) ? null : itinerary.get(lastJunction + 1);
    }   
}


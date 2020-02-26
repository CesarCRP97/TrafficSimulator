package simulator.model;

public class Vehicle extends SimulatedObject{

    private List<Junction> itinerary;
    private int maxSpeed;
    private int speed;
    private VehicleStatus status;
    private Road road;
    private int location;       //Posición en la carretera.
    private int contClass;
    private int totalCont;
    private int totalTravel;


    protected Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary ){
        super(id);
        //TODO complete
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
    void setSpeed(int s){}

    void setContClass(int c){}


    //Methods
    void moveToNextRoad(){}

    @Override
    void advance(int time) {

    }

    @Override
    public JSONObject report() {
        return null;
    }
}

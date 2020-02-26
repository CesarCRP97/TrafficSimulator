package simulator.model;

import java.util.List;

abstract public class Road extends SimulatedObject{
	Junction cruce_orig;
	Junction cruce_dest;
	int longitud;
	int vel_max;
	int limite_act;
	int alarm;
	Weather cond_amb;
	int total_contamination;
	private List <Vehicle> vehicle;
	
	

	Road(String id, Junction srcJunc,  Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather){
			super(id);
		// TODO Auto-generated constructor stub
	}
	
	void enter (Vehicle v) throws Exception {
		
	}

	
	void exit (Vehicle v) { this.vehicle.remove(v); }
	
	
	void setWeather (Weather w)  {
		
	}
	
	
	void addContamination (int c) {
	}
	
	
	abstract void reduceTotalContamination ();
	
	abstract void updateSpeedLimit (); 
	
	abstract int calculateVehicleSpeed (Vehicle v);
	
	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
	}

	
	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
   /* private Junction src;
    private Junction dest;
    private int length;         //Longitud
    private int maxSpeed;
    private int speedLimit;     //Limite de velocidad
    private int contAlarm;      //Alarma por contaminacion excesiva
    private Weather weather;
    private int totalCont;
    private List<Vehicle> vehicles;

    Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
        super(id);
        //Complete constructor
    }

    public int getLength(){
        return length;
    }

    int getMaxSpeed(){
        return maxSpeed;
    }

    public Junction getDest(){
        return dest;
    }

    public Junction getSrc() {
        return src;
    }

    void setWeather(Weather w){}

    void addContamination(int c){}

    void enter(Vehicle v){}

    void exit(Vehicle v){}

    public void advance(int t){}

    @Override
    public JSONObject report() {
        return null;
        //TODO
    }

    //Abstract methods
    abstract void reduceTotalContamination();
    abstract void updateSpeedLimit();
    abstract int calculateVehicleSpeed(Vehicle v);*/
}



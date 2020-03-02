package simulator.model;

<<<<<<< HEAD
import org.json.JSONObject;

=======
import java.util.Collections;
import java.util.Comparator;
>>>>>>> 639b762acda20d2d9f144e9a60de2c86d596e7d7
import java.util.List;
import java.util.ListIterator;

abstract public class Road extends SimulatedObject{
	
    private Junction src;
    private Junction dest;
    private int length;         //Longitud
    private int maxSpeed;
    private int speedLimit;     //Limite de velocidad
    private int contAlarm;      //Alarma por contaminacion excesiva
    private Weather weather;
    private int totalCont;
    private List<Vehicle> vehicles;

    class SortByLocation implements Comparator<Vehicle> {
		@Override
		public int compare(Vehicle v1, Vehicle v2) {
			// TODO Auto-generated method stub
			return v1.getLocation() - v2.getLocation();
		}
    	
    }
    
    
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
   
    public int getTotalCont(){
    	return totalCont;
    }
    
    public int getContAlarm(){
    	return contAlarm;
    }
    
    public int getSpeedLimit(){
    	return speedLimit;
    }
    	

    public Junction getDest(){
        return dest;
    }

    public Junction getSrc() {
        return src;
    }

    void enter(Vehicle v){
    	//if ((location && speed) == 0) de vehicle
    	//falta hacer excepcion con esta sentencia;
    	vehicles.add(v);
	}

    void exit(Vehicle v){
    	vehicles.remove(v);
    }
    
    void setWeather(Weather w){
    	this.weather = w; //lazar excepcion si w es null
	}

    void addContamination(int c){
    	this.totalCont = c; //unidades de CO2 si son negatv lanazar excepcion
	}
    
    //Abstract methods
    abstract void reduceTotalContamination();
    abstract void updateSpeedLimit();
    abstract int calculateVehicleSpeed(Vehicle v);

    public void advance(int t){
    	reduceTotalContamination();//reduce el valor de c
    	updateSpeedLimit();
    	//recorrido de la lista
        
    	for (int i=0; i<=vehicles.size(); i++ ) {
    		vehicles.get(i).setSpeed(maxSpeed);
    		vehicles.get(i).advance(t);
    	}
    	Collections.sort(vehicles, new SortByLocation());
>>>>>>> 639b762acda20d2d9f144e9a60de2c86d596e7d7
    }
    
    @Override
    public JSONObject report() {
        return null;
        //TODO
    }
		
	}
}




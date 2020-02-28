package simulator.model;

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

    void enter(Vehicle v){
    	//if ((location && speed) == 0) 
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
    	this.totalCont=c; //unidades de CO2 si son negatv lanazar excepcion 
	}
    
    //Abstract methods
    abstract void reduceTotalContamination();
    abstract void updateSpeedLimit();
    abstract int calculateVehicleSpeed(Vehicle v);

    public void advance(int t){
    	reduceTotalContamination();//reduce el valor de c
    	updateSpeedLimit();
    	//recorrido de la lista
    	for (int indice=0; indice<=vehicles.size(); indice++ );
    	
    	//falta actualizar la velocidad con el return de calculatevehiclespeed();
    	
    	Vehicle.advance(time);//llamada del metodo de la clase vehicle??
    	
    	//falta ordenar la lista de vehiculos con respecto a la localizacion;
    	
    }

    @Override
    public JSONObject report() {
        return null;
        //TODO
    }
		
	}
}




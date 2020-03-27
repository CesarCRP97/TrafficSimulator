package simulator.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


abstract public class Road extends SimulatedObject {

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
        if(srcJunc != null && destJunc != null && maxSpeed > 0 && contLimit >= 0 && length > 0 && weather != null) {
            this.src = srcJunc;
            this.dest = destJunc;
            this.length = length;
            this.speedLimit = maxSpeed;
            this.maxSpeed = maxSpeed;
            this.contAlarm = contLimit;
            this.weather = weather;
            this.vehicles = new ArrayList<>();
        }
        else
            throw new IllegalArgumentException("Not valid road parameters");
        //Complete constructor
    }

    public int getLength() {
        return length;
    }

    int getMaxSpeed() {
        return maxSpeed;
    }

    public void setSpeedLimit(int s) {
        speedLimit = s;
    }

    public int getTotalCont() {
        return totalCont;
    }

    public void setTotalCont(int tc){
        totalCont = tc;
    }

    public int getContAlarm() {
        return contAlarm;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }


    public Junction getDest() {
        return dest;
    }

    public Weather getWeather() {
        return weather;
    }

    void setWeather(Weather w) {
        if (w != null) {
            this.weather = w;
        } else {
            throw new IllegalArgumentException("weather shouldn't be null");
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Junction getSrc() {
        return src;
    }

    public JSONObject getReport() {
        return report();
    }

    void enter(Vehicle v) {

        if (v.getLocation() == 0 && v.getSpeed() == 0) {
            vehicles.add(v);
            v.setRoad(this);
        } else {
            throw new IllegalArgumentException("invalid Vehicle value");
        }
    }

    void exit(Vehicle v) {
        vehicles.remove(v);
    }

    void addContamination(int c) {
        if (c >= 0) {
            this.totalCont += c;
        } else {
            throw new IllegalArgumentException("invalid contamination value");
        }
    }

    //Abstract methods
    abstract void reduceTotalContamination();

    abstract void updateSpeedLimit();

    abstract int calculateVehicleSpeed(Vehicle v);

    public void advance(int t) {
        reduceTotalContamination();//reduce el valor de c
        updateSpeedLimit();
        //recorrido de la lista

        for (int i = 0; i < vehicles.size(); i++) {
            vehicles.get(i).setSpeed(calculateVehicleSpeed(vehicles.get(i)));
            vehicles.get(i).advance(t);
        }
        Collections.sort(vehicles, new SortByLocation());
    }

    @Override
    public JSONObject report() {
        JSONObject o = new JSONObject();
        o.put("id", getId());
        o.put("speedlimit", getSpeedLimit());
        o.put("weather", getWeather());
        o.put("co2", getTotalCont());

        JSONArray vArray = new JSONArray();
        o.put("vehicles", vArray);

        for (Vehicle v : getVehicles()) {
            vArray.put(v.getId());
        }
        return o;
    }

    static class SortByLocation implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle v1, Vehicle v2) {
            return v1.getLocation() - v2.getLocation();
        }
    }

}
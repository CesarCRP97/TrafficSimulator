package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import simulator.factories.Factory;
import simulator.model.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class Controller {

	private TrafficSimulator simulator;
    private Factory<Event> eventsFactory;

	public Controller(TrafficSimulator simulator, Factory<Event> eventsFactory) {
        if (simulator != null || eventsFactory != null) {
            this.simulator = simulator;
            this.eventsFactory = eventsFactory;
        } else {
            throw new IllegalArgumentException("invalid simulator or fatcory value");
        }
    }

    //Methods
    public void loadEvents(InputStream i) {
        JSONObject j = new JSONObject(new JSONTokener(i));
        if (!j.has("events") || j.getJSONArray("events") == null) {
            throw new IllegalArgumentException("invalid simulator or fatcory value");
        }
        else {
            JSONArray jo = j.getJSONArray("events");
            for (int k = 0; k < jo.length(); k++) {
                if (jo.getJSONObject(k) == null) {
                    throw new IllegalArgumentException("invalid simulator or fatcory value");
                }
                else {
                    Event e = eventsFactory.createInstance(jo.getJSONObject(k));
                    simulator.addEvent(e);
                }
            }
        }
    }

    public void run(int n) {
        for (int i = 0; i < n; i++) {
            this.simulator.advance();
        }
	}
	public List<Vehicle> getVehicles(){ return simulator.getVehicles();}
    public List<Road> getRoads(){ return simulator.getRoads();}

    public int getTime(){ return simulator.get_time();}

    public void reset() {
        this.simulator.reset();
    }
    
    public void addObserver(TrafficSimObserver o){
    	simulator.addObserver(o);
    }
    
    public void removeObserver(TrafficSimObserver o){
        simulator.removeObserver(o);
    }
    
    public void addEvent(Event e){
    	simulator.addEvent(e);
    }


}

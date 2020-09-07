package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimObserver;
import simulator.model.TrafficSimulator;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Controller {

    @SuppressWarnings("rawtypes")
	private TrafficSimulator simulator;
    private Factory<Event> eventsFactory;

    @SuppressWarnings("rawtypes")File
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

    public void run(int n, OutputStream out) {
        PrintStream p = new PrintStream(out);
        p.println("{");
        p.println("\"states\": [");
        for (int i = 0; i < n; i++) {
            this.simulator.advance();
            p.print(this.simulator.report());
            p.println(",\n");
        }
        p.println("]}");
    }

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

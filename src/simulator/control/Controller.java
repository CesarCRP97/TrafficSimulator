package simulator.control;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimulator;

public class Controller {
	
	private TrafficSimulator simulator;
	private Factory<Event> eventFact;

	public Controller(TrafficSimulator simulator, Factory<Event> eventFact) {
		if(simulator !=null || eventFact != null) {
			this.simulator = simulator;
			this.eventFact = eventFact;
		}
		else {
			throw new IllegalArgumentException ("invalid simulator or fatcory value" );
		}
	}

	//Getter&Setters
	public TrafficSimulator getSim() {
		return simulator;
	}

	public void setSim(TrafficSimulator simulator) {
		this.simulator = simulator;
	}

	public Factory<Event> getEventsFactory() {
		return eventFact;
	}

	public void setEventsFactory(Factory<Event> eventsFactory) {
		this.eventFact = eventFact;
	}

	public void loadEvents(InputStream i)  {
		JSONObject j = new JSONObject(new JSONTokener(i));
		if ( !j.has("events") || j.getJSONArray("events") == null ) {
			throw new IllegalArgumentException ("invalid simulator or fatcory value" );
		}
		else {
			JSONArray jo =  j.getJSONArray("events");
			for (int i = 0; i < jo.length(); i++) {
				if (jo.getJSONObject(i) == null) {
					throw new IllegalArgumentException ("invalid simulator or fatcory value" );
				}
				else {
					
					Event e = eventFact.createInstance(jo.getJSONObject(i));
					simulator.addEvent(e);
				}
			}
		}
	}

	public void run(int n, OutputStream out) throws Excepciones {
		PrintStream p = new PrintStream(out);
		p.println("{");
		p.println("\"states\": [");
		for (int i = 0; i<n; i++) {
			this.sim.advance();
			p.print(this.sim.report());
			p.print(",");
		}
		this.simulator.advance();
		p.print(this.simulator.report());
		p.println();
		p.println("]");
		p.println("}");
	}
	
	public void reset() {
		this.simulator.reset();
	}	
}

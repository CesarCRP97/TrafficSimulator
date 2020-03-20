package simulator.model;
import java.util.List;
import javafx.util.Pair;

public class SetWeatherEvent extends Event {
	
	private List<Pair<String, Weather>> ws;

	public SetWeatherEvent(int time, List<Pair<String,Weather>> ws) {
		super(time);
		this.ws = ws;
		if (ws != null);
		else throw new IllegalArgumentException ("invalid value" );
		// ...
	}
	
	void getPair (Pair<String,Weather> w) {
		w.Pai;
	}
	
    @Override
    void execute(simulator.model.RoadMap map) {
    	for (int w=0; w<ws.size(); w++) {
    		//TODO
    		ws.get(w.getFirst(map));
    		ws.get(w.getSecond(map));
    	}
    }
}

package simulator.model;
import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event {
	
	private List<Pair<String, Weather>> ws;


	public SetWeatherEvent(int time, List<Pair<String,Weather>> ws) {
		super(time);
		this.ws = ws;
		if (ws != null);
		else throw new IllegalArgumentException ("invalid value" );
		// ...
	}
	
    @Override
    void execute(simulator.model.RoadMap map) {

        for(Pair<String, Weather> pair : ws){
            Road r = map.getRoad(pair.getFirst());
            if(r != null){
                r.setWeather(pair.getSecond());
            }
            else
                throw new IllegalArgumentException("Road not registered.");
        }
    }
}

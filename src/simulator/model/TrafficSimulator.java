package simulator.model;

import java.util.List;

import org.json.JSONObject;
import simulator.misc.SortedArrayList;

public class TrafficSimulator<T> implements TrafficSimObserver, Observable<T> {
    private final RoadMap roadMap;
    private SortedArrayList<Event> eventList;
    private int _time;

    public TrafficSimulator() {
        roadMap = new RoadMap();
        eventList = new SortedArrayList<>();
        _time = 0;
    }

    public void addEvent(Event e) {
        eventList.add(e);
        onEventAdded(roadMap, eventList, e, _time);
    }

    public void advance(){
        _time++;
		onAdvanceStart(roadMap, eventList, _time);
		for (Event e : eventList) {
            if (e.getTime() == _time)
                e.execute(roadMap);
            //No hace falta eliminar los eventos, dado que _time siempre incrementa.
        }
        for (Junction j : roadMap.getJunctions()) {
            j.advance(_time);
        }
        for (Road r : roadMap.getRoads()) {
            r.advance(_time);
        }
        onAdvanceEnd(roadMap, eventList, _time);
    }

    public void reset() {
        roadMap.reset();
        eventList = new SortedArrayList<Event>();
        _time = 0;
        onReset(roadMap, eventList, _time);
    }

    public JSONObject report() {
        JSONObject o = new JSONObject();
        o.put("time", _time);
        o.put("state", roadMap.report());
        return o;
        //¿¿¿¿
        onRegister(roadMap, eventList, _time);
        //????
    }
    
	@Override
	public void onAdvanceStart(RoadMap Map, List<Event> events, int time) {
		
	}
	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
	}
	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		
	}
	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		
	}
	
	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		
	}
	@Override
	public void onError(String err) {
		
	}
	@Override
	public void addObserver(T o) {
		// TODO Auto-generated method stub
	}
	@Override
	public void removeObserver(T o) {
		// TODO Auto-generated method stub
	}
    
}

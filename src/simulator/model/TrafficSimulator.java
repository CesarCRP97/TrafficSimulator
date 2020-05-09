package simulator.model;

import java.util.List;

import org.json.JSONObject;
import simulator.misc.SortedArrayList;

public class TrafficSimulator<T> implements Observable<TrafficSimObserver> {

    private final RoadMap roadMap;
    private SortedArrayList<Event> eventList;
    private int _time;

    private TrafficSimObserver observer;

    public TrafficSimulator() {
        roadMap = new RoadMap();
        eventList = new SortedArrayList<>();
        _time = 0;
    }

    public void addEvent(Event e) {
        eventList.add(e);
        observer.onEventAdded(roadMap, eventList, e, _time);
    }

    public void advance(){
        _time++;
        observer.onAdvanceStart(roadMap, eventList, _time);
		for (Event e : eventList) {
            if (e.getTime() == _time)
                e.execute(roadMap);
            //No hace falta eliminar los eventos, dado que _time siempre incrementa.
        }
        for (Junction j : roadMap.getJunctions()) {
            try {
                j.advance(_time);
            }
            catch (Exception e){
                observer.onError(e.getMessage());
            }
        }
        for (Road r : roadMap.getRoads()) {
            try {
                r.advance(_time);
            }
            catch(Exception e){
                observer.onError(e.getMessage());
            }
        }
        observer.onAdvanceEnd(roadMap, eventList, _time);
    }


    public void reset() {
        roadMap.reset();
        eventList = new SortedArrayList<Event>();
        _time = 0;
        observer.onReset(roadMap, eventList, _time);
    }

    public JSONObject report() {
        JSONObject o = new JSONObject();
        o.put("time", _time);
        o.put("state", roadMap.report());
        return o;
    }

    @Override
    public void addObserver(TrafficSimObserver o) {
        observer = o;
        observer.onRegister(roadMap, eventList, _time);
    }

    @Override
    public void removeObserver(TrafficSimObserver o) {

    }
}

package simulator.model;
import org.json.JSONObject;
import simulator.misc.SortedArrayList;

import java.util.ArrayList;
import java.util.List;

public class TrafficSimulator implements Observable<TrafficSimObserver> {

    private final RoadMap roadMap;
    private SortedArrayList<Event> eventList;
    private int _time;

    private List<TrafficSimObserver> observerList;

    public TrafficSimulator() {
        roadMap = new RoadMap();
        eventList = new SortedArrayList<>();
        observerList = new ArrayList<>();
        _time = 0;
    }

    public List<Vehicle> getVehicles(){ return roadMap.getVehicles();}
    public List<Road> getRoads(){ return roadMap.getRoads();}
    public int get_time(){ return _time;}

    public void addEvent(Event e) {
        eventList.add(e);
        observerList.forEach(observer -> observer.onEventAdded(roadMap, eventList, e, _time));
    }

    public void advance(){
        _time++;
        observerList.forEach(observer -> observer.onAdvanceStart(roadMap, eventList, _time));

        //Al ser una lista ordenada respecto al tiempo, si el primero coincide, se ejecuta y elimina de la lista desplazando el resto.
        while(!eventList.isEmpty() && eventList.get(0).getTime() == _time){
            eventList.get(0).execute(roadMap);
            eventList.remove(0);
        }

        for (Junction j : roadMap.getJunctions()) {
            try {
                j.advance(_time);
            }
            catch (Exception e) {
                observerList.forEach(observer -> observer.onError(e.getMessage()));
            }
        }

        for (Road r : roadMap.getRoads()) {
            try {
                r.advance(_time);
            }
            catch(Exception e) {
                observerList.forEach(observer -> observer.onError(e.getMessage()));
            }
        }
        observerList.forEach(observer -> observer.onAdvanceEnd(roadMap, eventList, _time));
    }


    public void reset() {
        roadMap.reset();
        eventList.clear();
        _time = 0;
        observerList.forEach(observer -> observer.onReset(roadMap, eventList, _time));
    }

    public JSONObject report() {
        JSONObject o = new JSONObject();
        o.put("time", _time);
        o.put("state", roadMap.report());
        return o;
    }

    @Override
    public void addObserver(TrafficSimObserver o) {
        this.observerList.add(o);
        o.onRegister(roadMap, eventList, _time);
    }

    @Override
    public void removeObserver(TrafficSimObserver o) {

    }
}

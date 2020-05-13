package simulator.model;

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 1a1c926396dc23b95ad9a4d0c79687ff2059771a
import java.util.Iterator;
import java.util.List;

>>>>>>> 1a1c926396dc23b95ad9a4d0c79687ff2059771a
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

        //Al ser una lista ordenada respecto al tiempo, si el primero coincide, se ejecuta y elimina de la lista desplazando el resto.
        while(eventList.get(0).getTime() == _time && !eventList.isEmpty()){
            eventList.get(0).execute(roadMap);
            eventList.remove(0);
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
        eventList.clear();
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

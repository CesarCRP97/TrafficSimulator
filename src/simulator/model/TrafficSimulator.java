package simulator.model;

import org.json.JSONObject;
import simulator.misc.SortedArrayList;

import java.util.List;

public class TrafficSimulator {
    private RoadMap roadMap;
    private SortedArrayList<Event> eventList;
    private int _time;

    public TrafficSimulator(){
        roadMap = new RoadMap();
        eventList = new SortedArrayList<Event>();
        _time = 0;
    }

    public void addEvent(Event e){
        //TODO Mirar apuntes o documentación para ver cómo funciona la SortedArrayList.
        eventList.add(e);
    }

    public void advance(){
        //TODO
    }

    public void reset(){
        //TODO
    }

    public JSONObject report(){

        JSONObject o = new JSONObject();
        o.put("time", _time);
        o.put("state", roadMap.report());
        return o;
    }

}

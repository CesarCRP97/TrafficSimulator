package simulator.model;

import org.json.JSONObject;
import simulator.misc.SortedArrayList;

public class TrafficSimulator {
    private RoadMap roadMap;
    private SortedArrayList<Event> eventList;
    private int _time;

    public TrafficSimulator() {
        roadMap = new RoadMap();
        eventList = new SortedArrayList<Event>();
        _time = 0;
    }

    public void addEvent(Event e) {
        eventList.add(e);
    }

    public void advance() {
        _time++;

        for (Event e : eventList) {
            if (e.getTime() == _time)
                e.execute(roadMap);
        }

        for (Junction j : roadMap.getJunctions()) {
            j.advance(_time);
        }

        for (Road r : roadMap.getRoads()) {
            r.advance(_time);
        }

    }

    public void reset() {
        roadMap.reset();
        eventList = new SortedArrayList<Event>();
        _time = 0;
    }

    public JSONObject report() {

        JSONObject o = new JSONObject();
        o.put("time", _time);
        o.put("state", roadMap.report());
        return o;
    }

}

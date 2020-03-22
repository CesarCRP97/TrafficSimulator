package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.model.Event;
import simulator.model.NewVehicleEvent;

import java.util.List;

public class NewVehicleEventBuilder extends Builder<Event> {
    public NewVehicleEventBuilder() {
        super("new_vehicle");
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        JSONArray itinerary = data.getJSONArray("itinerary");

        List<String> list = null;
        for(Object o : itinerary){
            list.add(o.toString());
        }

        Event e = new NewVehicleEvent(data.getInt("time"), data.getString("id"), data.getInt("maxspeed"),
                                        data.getInt("class"), list
                                        );
        return e;
    }
}

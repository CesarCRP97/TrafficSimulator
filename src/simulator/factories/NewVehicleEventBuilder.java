package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.model.Event;
import simulator.model.NewVehicleEvent;

import java.util.ArrayList;
import java.util.List;

public class NewVehicleEventBuilder extends Builder<Event> {
    public NewVehicleEventBuilder() {
        super("new_vehicle");
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        JSONArray itinerary = data.getJSONArray("itinerary");

        List<String> list = new ArrayList<>();
        for(Object o : itinerary){
            list.add(o.toString());
        }

        return new NewVehicleEvent(data.getInt("time"), data.getString("id"), data.getInt("maxspeed"),
                                        data.getInt("class"), list
                                        );
    }
}

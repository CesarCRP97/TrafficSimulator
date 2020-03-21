package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;

public class NewInterCityRoadEventBuilder extends Builder<Event> {
    NewInterCityRoadEventBuilder(String type) {
        super(type);
    }

    @Override
    protected Event createTheInstance(JSONObject data) {

        return null;
    }
}

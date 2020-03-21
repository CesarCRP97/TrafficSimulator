package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;

public class NewCityRoadEventBuilder extends Builder<Event> {
    NewCityRoadEventBuilder(String type) {
        super(type);
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        return null;
    }
}

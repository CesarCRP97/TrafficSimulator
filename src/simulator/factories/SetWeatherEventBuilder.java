package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;

public class SetWeatherEventBuilder extends Builder<Event> {
    SetWeatherEventBuilder(String type) {
        super(type);
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        return null;
    }
}

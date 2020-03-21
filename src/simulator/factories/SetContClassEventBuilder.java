package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;

public class SetContClassEventBuilder extends Builder<Event> {
    SetContClassEventBuilder(String type) {
        super(type);
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        return null;
    }
}

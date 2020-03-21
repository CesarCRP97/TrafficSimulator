package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;

public class NewJunctionEventBuilder extends Builder <Event> {
    NewJunctionEventBuilder(String type) {
        super(type);
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        return null;
    }
}

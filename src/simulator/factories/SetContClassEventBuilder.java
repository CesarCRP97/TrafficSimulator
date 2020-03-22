package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;

public class SetContClassEventBuilder extends Builder<Event> {
    SetContClassEventBuilder() {
        super("set_cont_class");
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        return null;
    }   //TODO LO MISMO QUE CON EL SetWeatherEventBuilder.
}

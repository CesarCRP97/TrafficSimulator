package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;

public class SetWeatherEventBuilder extends Builder<Event> {
    public SetWeatherEventBuilder() {
        super("set_weather");
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        return null;
    }       //TODO HAY JSONs DENTRO DE el JSONARRAY "info". Parsear para convertir en una List<Pair<String, Weather>>.
}

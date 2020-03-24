package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

import java.util.List;

public class SetWeatherEventBuilder extends Builder<Event> {
    public SetWeatherEventBuilder() {
        super("set_weather");
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        JSONArray list = data.getJSONArray("info");
        List<Pair<String, Weather>> ws = null;

        //Extraemos cada JSONObject del JSONArray.
        for(int i = 0 ; i < list.length(); i++){
            JSONObject o  = list.getJSONObject(i);

            ws.add(extractPair(o));
        }
        return new SetWeatherEvent(data.getInt("time"), ws);
    }

    private Pair<String, Weather> extractPair(JSONObject o){
        String s = o.getString("road");
        Weather w = Weather.valueOf(o.getString("class"));
        return new Pair<>(s, w);
    }
}
                                
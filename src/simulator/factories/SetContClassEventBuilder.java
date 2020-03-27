package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetContClassEvent;

import java.util.ArrayList;
import java.util.List;

public class SetContClassEventBuilder extends Builder<Event> {
    public SetContClassEventBuilder() {
        super("set_cont_class");
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        JSONArray list = data.getJSONArray("info");
        List<Pair<String, Integer>> ws = new ArrayList<>();

        //Extraemos cada JSONObject del JSONArray.
        for(int i = 0 ; i < list.length(); i++){
            JSONObject o  = list.getJSONObject(i);

            ws.add(extractPair(o));
        }
        return new SetContClassEvent(data.getInt("time"), ws);
    }

    //Devuelve el Par (String, Integer) del JSONObject pasado.
    private Pair<String, Integer> extractPair(JSONObject o){
        String s = o.getString("vehicle");
        Integer i = o.getInt("class");
        return new Pair<>(s, i);
    }
}

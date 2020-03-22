package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.NewJunctionEvent;

import java.util.List;

public class NewJunctionEventBuilder extends Builder <Event> {
    private Factory<LightSwitchingStrategy> lssFactory;
    private Factory<DequeuingStrategy> dqsFactory;

    NewJunctionEventBuilder(Factory<LightSwitchingStrategy> lssFactory, Factory<DequeuingStrategy> dqsFactory ) {
        super("new_junction");
        this.lssFactory = lssFactory;
        this.dqsFactory = dqsFactory;
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        JSONArray coor = data.getJSONArray("coor");
        Event e = new NewJunctionEvent(data.getInt("time"), data.getString("id"),
                                        lssFactory.createInstance(data.getJSONObject("ls.strategy")),
                                        dqsFactory.createInstance(data.getJSONObject("dq_strategy")),
                                        coor.getInt(0),
                                        coor.getInt(1)
                                        );
        return e;
    }
}

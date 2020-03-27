package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.NewJunctionEvent;

public class NewJunctionEventBuilder extends Builder <Event> {
    private final Factory<LightSwitchingStrategy> lssFactory;
    private final Factory<DequeuingStrategy> dqsFactory;

    public NewJunctionEventBuilder(Factory<LightSwitchingStrategy> lssFactory, Factory<DequeuingStrategy> dqsFactory ) {
        super("new_junction");
        this.lssFactory = lssFactory;
        this.dqsFactory = dqsFactory;
    }

    @Override
    protected Event createTheInstance(JSONObject data) {
        JSONArray coor = data.getJSONArray("coor");
        return new NewJunctionEvent(data.getInt("time"), data.getString("id"),
                                        lssFactory.createInstance(data.getJSONObject("ls_strategy")),
                                        dqsFactory.createInstance(data.getJSONObject("dq_strategy")),
                                        coor.getInt(0),
                                        coor.getInt(1)
                                        );
    }
}

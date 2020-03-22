package simulator.factories;

import org.json.JSONObject;
import simulator.model.LightSwitchingStrategy;
import simulator.model.RoundRobinStrategy;

public class RoundRobinStrategyBuilder extends Builder<LightSwitchingStrategy> {
    public RoundRobinStrategyBuilder() {
        super("round_robin_lss");
    }

    @Override
    protected LightSwitchingStrategy createTheInstance(JSONObject data) {
        LightSwitchingStrategy s = new RoundRobinStrategy(data.getInt("timeslot"));
        return s;
    }
}

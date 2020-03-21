package simulator.factories;

import org.json.JSONObject;
import simulator.model.LightSwitchingStrategy;

public class RoundRobinStrategyBuilder extends Builder<LightSwitchingStrategy> {
    RoundRobinStrategyBuilder(String type) {
        super(type);
        //TODO
    }

    @Override
    protected LightSwitchingStrategy createTheInstance(JSONObject data) {
        return null;
        //TODO
    }
}

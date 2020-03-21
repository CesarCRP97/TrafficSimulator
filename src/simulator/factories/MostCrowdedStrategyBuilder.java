package simulator.factories;

import org.json.JSONObject;
import simulator.model.LightSwitchingStrategy;
import simulator.model.MostCrowdedStrategy;

public class MostCrowdedStrategyBuilder extends Builder<LightSwitchingStrategy> {
    MostCrowdedStrategyBuilder(String type) {
        super(type);
        //TODO
    }

    @Override
    protected LightSwitchingStrategy createTheInstance(JSONObject data) {
        MostCrowdedStrategy a = new MostCrowdedStrategy((data.get("data"));
        return a;
        //TODO
    }
}

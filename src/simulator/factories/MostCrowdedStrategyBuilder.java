package simulator.factories;

import org.json.JSONObject;
import simulator.model.LightSwitchingStrategy;
import simulator.model.MostCrowdedStrategy;

public class MostCrowdedStrategyBuilder extends Builder<LightSwitchingStrategy> {
    public MostCrowdedStrategyBuilder() {
        super("most_crowded_lss");
    }

    @Override
    protected LightSwitchingStrategy createTheInstance(JSONObject data) {
        MostCrowdedStrategy s = new MostCrowdedStrategy((data.getInt("timeslot")));
        return s;

    }
}

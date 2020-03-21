package simulator.factories;

import org.json.JSONObject;
import simulator.model.DequeuingStrategy;

public class MoveAllStrategyBuilder extends Builder<DequeuingStrategy> {

    MoveAllStrategyBuilder(String type) {
        super(type);
        //TODO
    }

    @Override
    protected DequeuingStrategy createTheInstance(JSONObject data) {
        //TODO
        return null;
    }
}

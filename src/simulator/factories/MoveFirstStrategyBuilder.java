package simulator.factories;

import org.json.JSONObject;
import simulator.model.DequeuingStrategy;

public class MoveFirstStrategyBuilder extends Builder<DequeuingStrategy> {
    MoveFirstStrategyBuilder(String type) {
        super(type);
        //TODO
    }

    @Override
    protected DequeuingStrategy createTheInstance(JSONObject data) {
        return null;
        //TODO
    }
}

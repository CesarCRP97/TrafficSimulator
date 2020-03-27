package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class MoveAllStrategy implements DequeuingStrategy {

    public MoveAllStrategy() {}

    @Override
    public List<Vehicle> dequeue(List<Vehicle> vs) {
        return new ArrayList<>(vs);
    }
}

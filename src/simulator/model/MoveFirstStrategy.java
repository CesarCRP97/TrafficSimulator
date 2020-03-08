package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class MoveFirstStrategy implements DequeuingStrategy {

    public MoveFirstStrategy(){};

    @Override
    public List<Vehicle> dequeue(List<Vehicle> q) {
        List<Vehicle> list = new ArrayList<>();
        list.add(q.get(0));
        return list;
    }
}

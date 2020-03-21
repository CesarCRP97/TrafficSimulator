package simulator.model;

import java.util.List;

public interface DequeuingStrategy {
    abstract List<Vehicle> dequeue(List<Vehicle> vs);
}

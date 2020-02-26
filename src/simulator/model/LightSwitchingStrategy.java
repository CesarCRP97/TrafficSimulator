package simulator.model;

import java.util.List;

public interface LightSwitchingStrategy {
    abstract int chooseNextGreen(List<Road> rs, List<List<Vehicle>> qs, int i0, int i1, int i2);
}

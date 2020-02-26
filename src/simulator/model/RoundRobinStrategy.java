package simulator.model;

import java.util.List;

public class RoundRobinStrategy implements LightSwitchingStrategy {

    public RoundRobinStrategy(int i){}

    @Override
    public int chooseNextGreen(List<Road> rs, List<List<Vehicle>> qs, int i0, int i1, int i2) {
        return 0;
    }
}

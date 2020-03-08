package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LightSwitchingStrategy {

    private int timeSlot;

    public MostCrowdedStrategy(int i){
        timeSlot = i;
    }

    @Override
    public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime, int currTime) {
        if(roads.isEmpty()) return -1;
        if(currGreen == -1)
            return firstMostCrowdedQueue(qs);

        if(currTime - lastSwitchingTime < timeSlot) return currGreen;
        else{
            return nextMostCrowded(qs, currGreen);
        }
    }

    private int firstMostCrowdedQueue(List<List<Vehicle>> qs){
        int i = 0, mostCrowded = 0;
        //TODO Seguro que se puede hacer con un iterator. Y poner en un método privado auxiliar.
        while(i < qs.size()){
            if(qs.get(i).size() > mostCrowded) mostCrowded = i;
            i++;
        }
        return mostCrowded;
    }

    private int nextMostCrowded(List<List<Vehicle>> qs, int currGreen) {
        //inicio en currGreen + 1. El más lleno empieza siendo el actual.
        int i = currGreen + 1, nextGreen = currGreen;
        while (i % qs.size() != currGreen) {
            if (qs.get(i).size() > qs.get(nextGreen).size()) nextGreen = i;
            i++;
        }
        return nextGreen;
    }


}

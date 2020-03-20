package simulator.model;

import simulator.misc.Pair;

import java.util.List;

public class SetContClassEvent extends Event {

    private List<Pair<String, Integer>> cs;

    public SetContClassEvent(int time, List<Pair<String,Integer>> cs){
        super(time);
        if(cs != null) {
            this.cs = cs;
        }
        else
            throw new IllegalArgumentException("List given is null");
    }

    @Override
    void execute(simulator.model.RoadMap map) {
        for(Pair<String, Integer> pair : cs){
            Vehicle v = map.getVehicle(pair.getFirst());
            if(v != null){
                v.setContClass(pair.getSecond());
            }
            else
                throw new IllegalArgumentException("Vehicle not valid");
        }
    }
}

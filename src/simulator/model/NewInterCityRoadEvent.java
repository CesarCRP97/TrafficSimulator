package simulator.model;

public class NewInterCityRoadEvent extends NewRoadEvent {

    public NewInterCityRoadEvent(int i, String s, String s1, String s2, int i1, int i2, int i3, Weather w) {
        super(i, s, s1, s2, i1, i2, i3, w);
    }

    @Override
    void execute(simulator.model.RoadMap map) {
        //TODO
    }

    private Road createRoadMap(){
        //TODO
        return null;
    }
}

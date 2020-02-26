package simulator.model;

abstract class NewRoadEvent extends  Event {

    public NewRoadEvent(int i, String s, String s1, String s2, int i1, int i2, int i3, Weather w){
        super();
    }


    @Override
    void execute(simulator.model.RoadMap map) {
            //TODO
    }

    abstract private Road createRoadObject(){
            //TODO
    }

}

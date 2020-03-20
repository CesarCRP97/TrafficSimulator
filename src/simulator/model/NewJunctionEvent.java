package simulator.model;

public class NewJunctionEvent extends Event {
    private String id;
    private LightSwitchingStrategy lsStrategy;
    private DequeuingStrategy dqStategy;
    private int xCoor, yCoor;


    public NewJunctionEvent(int time, String id, LightSwitchingStrategy
            lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
        super(time);
        this.id = id;
        this.lsStrategy = lsStrategy;
        this.dqStategy = dqStrategy;
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }

    @Override
    void execute(RoadMap map) {
        Junction j = new Junction(id, lsStrategy, dqStategy, xCoor, yCoor);
        map.addJunction(j);
    }
}

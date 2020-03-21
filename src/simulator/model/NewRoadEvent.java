package simulator.model;

abstract class NewRoadEvent extends Event {
    private String id;
    private String srcJunc;
    private String destJunc;
    private int length;
    private int co2Limit;
    private int maxSpeed;
    private Weather weather;

    public NewRoadEvent(int time, String id, String srcJun, String
            destJunc, int length, int co2Limit, int maxSpeed, Weather weather) {
        super(time);
        this.id = id;
        this.srcJunc = srcJun;
        this.destJunc = destJunc;
        this.length = length;
        this.co2Limit = co2Limit;
        this.maxSpeed = maxSpeed;
        this.weather = weather;

    }


    @Override
    void execute(RoadMap map) {
        Road r = createRoadObject(map, id, srcJunc, destJunc, length, co2Limit, maxSpeed, weather);
        map.addRoad(r);
    }

    abstract Road createRoadObject(RoadMap map, String id, String srcJun, String
            destJunc, int length, int co2Limit, int maxSpeed, Weather weather);
}

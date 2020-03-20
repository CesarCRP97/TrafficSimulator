package simulator.model;

public class NewCityRoadEvent extends NewRoadEvent {


    public NewCityRoadEvent(int time, String id, String srcJun, String
            destJunc, int length, int co2Limit, int maxSpeed, Weather weather){
        super(time, id, srcJun, destJunc, length, co2Limit, maxSpeed, weather);
    }
    @Override
    void execute(simulator.model.RoadMap map) {
        super.execute(map);
    }

    @Override
    Road createRoadObject(RoadMap map, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed, Weather weather) {
        return new CityRoad(id, map.getJunction(srcJun), map.getJunction(destJunc), maxSpeed, co2Limit, length, weather);
    }


}

package simulator.model;

public class InterCityRoad extends Road {

    InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
        super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
    }

    @Override
    void reduceTotalContamination() {
        //TODO
    }

    @Override
    void updateSpeedLimit() {

    }

    @Override
    int calculateVehicleSpeed(Vehicle v) {

    }
}

package simulator.model;

public class CityRoad extends Road {
    CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
        super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
    }

    @Override
    void reduceTotalContamination() {

    }

    @Override
    void updateSpeedLimit() {
        //Does nothing
    }

    @Override
    int calculateVehicleSpeed(Vehicle v) {
        return (int)(((11.0 - v.getContClass()) / 11.0) * getMaxSpeed());
    }
}

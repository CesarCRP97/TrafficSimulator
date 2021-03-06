package simulator.model;

public class InterCityRoad extends Road {

    InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
        super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
    }

    @Override
    void reduceTotalContamination() {
        Weather x = this.getWeather();
        int tc = getTotalCont();
        if (x == Weather.SUNNY) {
            tc = (int) (((100.0 - 2) / 100.0) * tc);
        } else if (x == Weather.CLOUDY) {
            tc = (int) (((100.0 - 3) / 100.0) * tc);
        } else if (x == Weather.RAINY) {
            tc = (int) (((100.0 - 10) / 100.0) * tc);
        } else if (x == Weather.WINDY) {
            tc = (int) (((100.0 - 15) / 100.0) * tc);
        } else if (x == Weather.STORM) {
            tc = (int) (((100.0 - 20) / 100.0) * tc);
        }
        setTotalCont(tc);
    }

    @Override
    void updateSpeedLimit() {
        int maxSpeed = getMaxSpeed();
        if (getTotalCont() > getContAlarm()) {
            maxSpeed = (int) (getMaxSpeed() * 0.5);
            this.setSpeedLimit(maxSpeed);
        }
        this.setSpeedLimit(maxSpeed);
    }

    @Override
    int calculateVehicleSpeed(Vehicle v) {
        int vehicleSpeed = this.getMaxSpeed();
        if (this.getWeather() == Weather.STORM) {
            vehicleSpeed = (int) (this.getSpeedLimit() * 0.8);
        }
        return vehicleSpeed;
    }
}

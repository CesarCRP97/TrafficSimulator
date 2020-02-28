package simulator.model;

public class InterCityRoad extends Road {

    InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
        super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
    }

    @Override
    void reduceTotalContamination() {
    	Weather weather;
    	
    	setWeather sun = new setWeather(Weather.SUNNY);
    	
    	
    	
    	enumWeather
    	
    	enumWeather
    	
    	enumWeather
    	
    	int tc;
    	tc = (int) (((100.0 - this.setWeather()) / 100.0) * tc);
    }
    	
        //TODO
    @Override
    void updateSpeedLimit() {

    }

    @Override
    int calculateVehicleSpeed(Vehicle v) {

    }
}

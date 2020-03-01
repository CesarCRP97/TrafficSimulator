package simulator.model;

public class CityRoad extends Road {
    CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
        super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
    }

    @Override
    void reduceTotalContamination(){
    	Weather x = null;
    	int tc = getTotalCont();//tc es el valor de la cantaminacion actual
    	if(x == Weather.SUNNY) { tc = (int) (((100.0 - 2) / 100.0) * tc); }
    	if(x == Weather.CLOUDY) { tc = (int) (((100.0 - 10) / 100.0) * tc); }
    	if(x == Weather.RAINY) { tc = (int) (((100.0 - 2) / 100.0) * tc); }
    	if(x == Weather.WINDY) { tc = (int) (((100.0 - 10) / 100.0) * tc); }
    	if(x == Weather.STORM) { tc = (int) (((100.0 - 2) / 100.0) * tc); }
    	
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

package simulator.model;

public class InterCityRoad extends Road {

    InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
        super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
    }

    @Override
    void reduceTotalContamination() {
    	Weather x = null;
    	int tc = getTotalCont();//tc es el valor de la cantaminacion actual
    	if(x == Weather.SUNNY) { tc = (int) (((100.0 - 2) / 100.0) * tc); }
    	else if(x == Weather.CLOUDY) { tc = (int) (((100.0 - 3) / 100.0) * tc); }
    	else if(x == Weather.RAINY) { tc = (int) (((100.0 - 10) / 100.0) * tc); }
    	else if(x == Weather.WINDY) { tc = (int) (((100.0 - 15) / 100.0) * tc); }
    	else if(x == Weather.STORM) { tc = (int) (((100.0 - 20) / 100.0) * tc); }
    }	
    
        //TODO
    @Override
    void updateSpeedLimit() {
    	int maxspd;
    	if( getTotalCont() > getContAlarm()) {
    	maxspd = (int)(getMaxSpeed() * 0.5); 
    	}
    	else {
    		getMaxSpeed();
    	} 
    }
    
    @Override
    int calculateVehicleSpeed(Vehicle v) {
    	v.getSpeed();
    	int cvspeed;
    	cvspeed = v.getSpeed();
    	Weather x = null;
    	if (x == Weather.STORM) { cvspeed = (int)(this.getSpeedLimit()*0.8);};
    	return cvspeed;
    }
}
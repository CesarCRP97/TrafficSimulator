package simulator.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class RoadMap {
    //private static final Road Road = null;
    protected List<Junction> junctions;
    protected List<Road> roads;
    protected List<Vehicle> vehicles;
    protected Map<String, Junction> junctionsM;
    protected Map<String, Road> roadsM;
    protected Map<String, Vehicle> vehiclesM;

    protected RoadMap() {
    }

    void addJunction(Junction j) {
        if (junctionsM.get(j.getId()) == null) {
            junctions.add(j);
            junctionsM.put(j.getId(), j);
        } else {
            throw new IllegalArgumentException("Junction already existing");
        }

    }


    void addRoad(Road r) {
        if (validRoad(r)) {
            roads.add(r);
            roadsM.put(r.getId(), r);

            //Añade la carretera a los Junction
            r.getSrc().addOutgoingRoad(r);
            r.getDest().addIncomingRoad(r);
        } else {
            throw new IllegalArgumentException("the new road is invalid");
        }
    }


    void addVehicle(Vehicle v) {
        if (vehiclesM.get(v.getId()) != null) {
            throw new IllegalArgumentException("Already existing Vehicle");
        }
        if (validItinerary(v)) {// throw;
            vehicles.add(v);
            vehiclesM.put(v.getId(), v);
        } else {
            throw new IllegalArgumentException("Invalid Vehicle Value");
        }
    }

    public Junction getJunction(String id) {
        return junctionsM.get(id);
    }

    public Road getRoad(String id) {
        return roadsM.get(id);
    }

    public Vehicle getVehicle(String id) {
        return vehiclesM.get(id);
    }

    public List<Junction> getJunctions() {
        return junctions;
    }


    public List<Road> getRoads() {
        return roads;
    }


    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    void reset() {
        junctions.clear();
        roads.clear();
        vehicles.clear();

        junctionsM.clear();
        roadsM.clear();
        vehiclesM.clear();
    }

    public JSONObject report() {
        JSONObject o = new JSONObject();
        JSONArray rArray = new JSONArray();

        o.put("roads", rArray);
        for (Road r : getRoads()) {
            rArray.put(r.getReport());
        }

        JSONArray vArray = new JSONArray();
        o.put("vehicles", vArray);
        for (Vehicle v : getVehicles()) {
            vArray.put(v.getReport());
        }
        return o;
    }

    private boolean validItinerary(Vehicle v) {
        List<Junction> itinerary = v.getItinerary();
        for (int i = 0; i < itinerary.size() - 1; i++) {
            if (itinerary.get(i).roadTo(itinerary.get(i + 1)) == null) return false;
        }
        return true;
    }

    private boolean validRoad(Road r) {
        return junctionsM.get(r.getDest().getId()) != null && junctionsM.get(r.getSrc().getId()) != null && roadsM.get(r.getId()) == null;
    }


}

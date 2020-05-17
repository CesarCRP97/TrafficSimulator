package simulator.view;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class VehiclesTableModel extends AbstractTableModel implements TrafficSimObserver {

    List<Vehicle> _vehicles;
    String[] _columnNames = {"Id", "Location", "Itinerary", "CO2 Class", "Max. Speed", "Speed", "Total CO2", "Distance"};

    public VehiclesTableModel(Controller ctr){
        _vehicles = new ArrayList<>();
        ctr.addObserver(this);
    }
    public void update() {
        fireTableDataChanged();;
    }

    public void setVehiclesList(List<Vehicle> vehicles){
        _vehicles = vehicles;
        update();
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public String getColumnName(int col){return _columnNames[col];}


    @Override
    public int getColumnCount() {return _columnNames.length;}

    @Override
    public int getRowCount() {
        return _vehicles == null ? 0 : _vehicles.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object s = null;
        switch (columnIndex){
            case 0:
                s = _vehicles.get(rowIndex).getId();
                break;
            case 1:
                Vehicle v = _vehicles.get(rowIndex);
                s = v.getRoad().toString() + ":" + v.getLocation();
                break;
            case 2:
                s = _vehicles.get(rowIndex).getItinerary();
                break;
            case 3:
                s = _vehicles.get(rowIndex).getContClass();
                break;
            case 4:
                s = _vehicles.get(rowIndex).getMaxSpeed();
                break;
            case 5:
                s = _vehicles.get(rowIndex).getSpeed();
                break;
            case 6:
                s = _vehicles.get(rowIndex).getTotalCont();
                break;
            case 7:
                s = _vehicles.get(rowIndex).getTotalTravel();
                break;
            }
        return s;
    }

    @Override
    public void onAdvanceStart(RoadMap map, List<Event> events, int time) {}

    @Override
    public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
        setVehiclesList(map.getVehicles());
    }

    @Override
    public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {}

    @Override
    public void onReset(RoadMap map, List<Event> events, int time) {
        setVehiclesList(map.getVehicles());
    }

    @Override
    public void onRegister(RoadMap map, List<Event> events, int time) {
        setVehiclesList(map.getVehicles());
    }

    @Override
    public void onError(String err) {

    }
}

package simulator.view;

import simulator.control.Controller;
import simulator.model.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class RoadsTableModel extends AbstractTableModel implements TrafficSimObserver {

    List<Road> _roads;
    String[] _columnNames = {"Id", "Length", "Weather", "Max. Speed", "Speed Limit", "Total CO2", "CO2 Limit"};

    public RoadsTableModel(Controller ctr){
        _roads = new ArrayList<>();
        ctr.addObserver(this);
    }
    public void update() {
        fireTableDataChanged();;
    }

    public void setRoadsList(List<Road> roads){
        _roads = roads;
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
        return _roads == null ? 0 : _roads.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object s = null;
        switch (columnIndex){
            case 0:
                s = _roads.get(rowIndex).getId();
                break;
            case 1:
                s = _roads.get(rowIndex).getLength();
                break;
            case 2:
                s = _roads.get(rowIndex).getWeather();
                break;
            case 3:
                s = _roads.get(rowIndex).getMaxSpeed();
                break;
            case 4:
                s = _roads.get(rowIndex).getSpeedLimit();
                break;
            case 5:
                s = _roads.get(rowIndex).getTotalCont();
                break;
            case 6:
                s = _roads.get(rowIndex).getContAlarm();
                break;
        }
        return s;
    }

    @Override
    public void onAdvanceStart(RoadMap map, List<Event> events, int time) {}

    @Override
    public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
        setRoadsList(map.getRoads());
    }

    @Override
    public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {}

    @Override
    public void onReset(RoadMap map, List<Event> events, int time) {
        setRoadsList(map.getRoads());
    }

    @Override
    public void onRegister(RoadMap map, List<Event> events, int time) {
        setRoadsList(map.getRoads());
    }

    @Override
    public void onError(String err) {

    }
}

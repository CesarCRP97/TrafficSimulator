package simulator.view;

import simulator.control.Controller;
import simulator.model.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class JunctionsTableModel extends AbstractTableModel implements TrafficSimObserver {

    private List<Junction> _junctions;
    private String[] _columnNames = { "Id", "Green", "Queues"};

    public JunctionsTableModel(Controller ctr){
        _junctions = new ArrayList<>();
        ctr.addObserver(this);
    }

    public void update() {
        fireTableDataChanged();;
    }

    public void setJunctionsList(List<Junction> junctions){
        _junctions = junctions;
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
        return _junctions == null ? 0 : _junctions.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object s = null;
        switch (columnIndex){
            case 0:
                s = _junctions.get(rowIndex).getId();
                break;
            case 1:
                Junction j = _junctions.get(rowIndex);
                s = j.getGreenLightIndex() == -1 ? "NONE" : j.getInRoads().get(j.getGreenLightIndex()). getId();
                break;
            case 2:
                Junction junction = _junctions.get(rowIndex);
                List<Road> roads = junction.getInRoads();
                String a = new String();
                for(int i = 0; i < roads.size(); i++){
                    a += roads.get(i).toString() + ":" + junction.getQueue(i).toArray().toString() + " ";
                }
                s = a;
                break;
        }
        return s;
    }

    @Override
    public void onAdvanceStart(RoadMap map, List<Event> events, int time) {}

    @Override
    public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
        setJunctionsList(map.getJunctions());
    }

    @Override
    public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {}

    @Override
    public void onReset(RoadMap map, List<Event> events, int time) {
        setJunctionsList(map.getJunctions());
    }

    @Override
    public void onRegister(RoadMap map, List<Event> events, int time) {
        setJunctionsList(map.getJunctions());
    }

    @Override
    public void onError(String err) {}
}

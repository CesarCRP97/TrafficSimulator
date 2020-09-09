package simulator.view;

import extra.jtable.EventEx;
import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class EventsTableModel extends AbstractTableModel implements TrafficSimObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Event> _events;
	private String[] _columnNames = { "#", "Time", "Description" };

	public EventsTableModel(Controller ctr) {
		_events = new ArrayList<>();
		ctr.addObserver(this);
	}

	public void update() {
		fireTableDataChanged();
	}
	
	public void setEventsList(List<Event> events) {
		_events = events;
		update();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	//this is for the column header
	@Override
	public String getColumnName(int col) {
		return _columnNames[col];
	}

	@Override
	// método obligatorio, probad a quitarlo, no compila
	//
	// this is for the number of columns
	public int getColumnCount() {
		return _columnNames.length;
	}

	@Override
	// método obligatorio
	//
	// the number of row, like those in the events list
	public int getRowCount() {
		return _events == null ? 0 : _events.size();
	}

	@Override
	// método obligatorio
	// así es como se va a cargar la tabla desde el ArrayList
	// el índice del arrayList es el número de fila pq en este ejemplo
	// quiero enumerarlos.
	//
	// returns the value of a particular cell 
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = rowIndex;
			break;
		case 1:
			s = _events.get(rowIndex).getTime();
			break;
		case 2:
			s = _events.get(rowIndex).toString();
			break;
		}
		return s;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {

	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {

	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		setEventsList(events);
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		setEventsList(events);
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		setEventsList(events);
	}

	@Override
	public void onError(String err) {}
}

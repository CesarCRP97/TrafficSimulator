package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.util.Pair;
import simulator.control.ControlPanel;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Weather;

public class StatusBar extends JPanel implements TrafficSimObserver{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void StatusBarPanel (ControlPanel ticks) {
		JPanel status = new JPanel(new BorderLayout());
		status.add(TimeAndEvents(), BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	public Component TimeAndEvents() {
		JPanel te = new JPanel(new BorderLayout());
		te.add(SetTime(null),BorderLayout.WEST);
		te.add(SetEvent(null), BorderLayout.EAST);
		return te;
	}
	
	private Component SetEvent(Event e) {
		ChangeWeatherDialog cw;
		ChangeCO2ClassDialog co;
		
		if( == getselected) {
			
		}
		return null;
	}

	private Component SetTime(ControlPanel c) {
		e.toString();
		return null;
	}
	
	
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		
	}
	

}

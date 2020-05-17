package simulator.view;

import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class StatusBar extends JPanel implements TrafficSimObserver{

	//TODO : Creo que a la constructora se la pasa un parámetro Controller, no ControlPanel.
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

	
	private Component SetEvent(ActionEvent e){
		ChangeWeatherDialog cw = new ChangeWeatherDialog(); 
		ChangeCO2ClassDialog co = new ChangeCO2ClassDialog();
		//cuando el usuaro le da ok ya sea change weather o cos muestra ell nombre de cual de los dos fue
		if (e.getSource() == cw.OkButton(null)) {
			System.out.print("Event added:" + cw.getName());
		}
		else if (e.getSource() == co.OkButton(null)) {
			System.out.print("Event added:" + co.getName());
		}
		//otra manera q pense en hacerlo da error en 
		/*
		if (((Object) cw.OkButton(paramString())).isSelected()) {
			System.out.print("Event added:" + cw.getName());
		}
		else if (((Object) co.OkButton(paramString())).isSelected()) {
			System.out.print("Event added:" + co.getName());
		}*/
		return null;
	}
	//TO DO 
	private Component SetTime(ControlPanel e){
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

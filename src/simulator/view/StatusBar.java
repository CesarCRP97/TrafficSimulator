package simulator.view;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

import javax.swing.*;
import java.util.List;

public class StatusBar extends JPanel implements TrafficSimObserver{

	private JTextArea timeText;
	private JTextArea eventText;


	public StatusBar(Controller _ctr){
		this.StatusBarPanel();
		_ctr.addObserver(this);
	}

	//TODO : Creo que a la constructora se la pasa un parámetro Controller, no ControlPanel.
	public void StatusBarPanel() {
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.LINE_AXIS));

		this.addTimeText(statusPanel, 0);
		this.addEventText(statusPanel);

		this.setVisible(true);
	}

	private void addTimeText(JPanel panel, int time){
		JTextArea timeText = new JTextArea("Time: " + time);
		timeText.setEditable(false);
		timeText.setVisible(true);
		panel.add(timeText);
		this.timeText = timeText;
	}

	private void addEventText(JPanel panel){
		JTextArea eventText = new JTextArea("");
		eventText.setEditable(false);
		eventText.setVisible(true);
		panel.add(eventText);
		this.eventText = eventText;
	}

	
	private void setEventText(String s){
		eventText.setText(s);
	}

	private void setTimeText(int time){
		timeText.setText("Time : " + time);
	}
	
	
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		this.setTimeText(time);
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		this.setEventText("Event added: " + e.toString());
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		this.setTimeText(time);
		this.setEventText("Simulator reset");
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {}

	@Override
	public void onError(String err) {
		setEventText(err);
	}
	

}

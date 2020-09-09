package simulator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;

import simulator.misc.Pair;
import simulator.control.Controller;
import simulator.model.*;

public class ChangeWeatherDialog extends JDialog{


	private static final long serialVersionUID = 1L;
	private JComboBox road;
	private JComboBox<Weather> weather;
	private JSpinner ticks;

	private Controller controller;

	protected ChangeWeatherDialog(Controller cont) {
		super();
		this.setTitle("Cambio de condiciones atmosféricas");
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		controller = cont;

		this.initGUI();

		this.pack(); // ajusta la ventana al tama�o de las componentes
		this.setVisible(true);
	}


	private void initGUI(){
		JPanel mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(this.message(), BorderLayout.NORTH);
		mainPanel.add(createCentralPanel(), BorderLayout.CENTER);
		mainPanel.add(createInferiorPanel(), BorderLayout.SOUTH);

		this.add(mainPanel);


	}

	private Component message() {
		JTextArea text = new JTextArea("Schedule an event to change the weather of a road after a given number of simulation ticks from now.");
		text.setEditable(false);
		text.setVisible(true);
		text.setLineWrap(true);
		text.setMinimumSize(new Dimension(300, 100));

		return text;
	}

	private JPanel createCentralPanel(){
		JPanel centralPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));

		centralPanel.add(new JTextArea("Road: "));
		centralPanel.add(roadComboBox(this.controller.getRoads()));

		centralPanel.add(new JTextArea("Weather: "));
		centralPanel.add(weatherComboBox());

		centralPanel.add(new JTextArea("Ticks: "));
		centralPanel.add(ticksSpinner());

		return centralPanel;
	}

	//Road options
	private Component roadComboBox (List<Road> roads) {
		road = new JComboBox(roads.toArray());

		road.setEditable(false);

		return road;
	}

	//TODO: Probar y cambiar por array manual.
	//Weather class options
	private Component weatherComboBox() {
		weather= new JComboBox<>(Weather.values());
		weather.setSelectedIndex(0);
		weather.setEditable(false);
		weather.setMinimumSize(new Dimension(300, 40));

		return weather;
	}

	//ticks options
	private Component ticksSpinner () {
		ticks = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
		ticks.setMinimumSize(new Dimension(100, 40));

		return ticks;
	}

	private Component createInferiorPanel() {
		JPanel infPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
		infPanel.add(okButton());
		infPanel.add(cancelButton());

		infPanel.setPreferredSize(new Dimension(500, 75));

		return infPanel;
	}

	private Component cancelButton() {
		JButton cancel = new JButton();
		cancel.setText("CANCEL");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {setVisible(false);}
		});
		cancel.setMinimumSize(new Dimension(60, 30));
		return cancel;
	}

	//TODO OK BUTTON
	Component okButton () {

		JButton ok = new JButton();
		ok.setText("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Pair<String, Weather>> cs = new ArrayList<>();
				cs.add(new Pair(road.getSelectedItem().toString(), weather.getSelectedItem()));
				SetWeatherEvent event = new SetWeatherEvent(controller.getTime() + (Integer)ticks.getValue(), cs);
				controller.addEvent(event);

				setVisible(false);
			}
		});
		ok.setMinimumSize(new Dimension(60, 30));
		return ok;
	}
}

















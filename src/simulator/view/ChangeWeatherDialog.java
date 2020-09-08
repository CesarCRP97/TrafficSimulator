package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
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
	private JComboBox weather;
	private JSpinner ticks;

	private Controller controller;

	protected ChangeWeatherDialog(Controller cont) {
		super();
		this.setTitle("Cambio de condiciones atmosféricas");
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		controller = cont;

		this.initGUI();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		return text;
	}

	private JPanel createCentralPanel(){
		JPanel centralPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		centralPanel.add(roadComboBox(this.controller.getRoads()));
		centralPanel.add(weatherComboBox());
		centralPanel.add(ticksSpinner());

		return centralPanel;
	}

	//Road options
	private Component roadComboBox (List<Road> roads) {
		road = new JComboBox(roads.toArray());
		road.setSelectedIndex(0);
		road.setEditable(false);

		return road;
	}

	//TODO: Probar y cambiar por array manual.
	//Weather class options
	private Component weatherComboBox() {
		String[] weathers = (String[])Arrays.stream(Weather.values()).map(Enum::name).collect(Collectors.toList()).toArray();
		weather= new JComboBox(weathers);
		weather.setSelectedIndex(0);
		weather.setEditable(false);

		return weather;
	}

	//ticks options
	private Component ticksSpinner () {
		ticks = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
		return ticks;
	}

	private Component createInferiorPanel() {
		JPanel infPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
		infPanel.add(okButton());
		infPanel.add(cancelButton());

		return infPanel;
	}

	private Component cancelButton() {
		JButton cancel = new JButton();
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {setVisible(false);}
		});
		return cancel;
	}

	//TODO OK BUTTON
	Component okButton () {

		JButton ok = new JButton();
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Pair<String, Weather>> cs = new ArrayList<>();
				cs.add(new Pair(road.getSelectedItem().toString(), weather.getSelectedItem()));
				SetWeatherEvent event = new SetWeatherEvent(controller.getTime() + Integer.parseInt((String)ticks.getValue()), cs);
				controller.addEvent(event);

				setVisible(false);
			}
		});
		return ok;
	}
}

















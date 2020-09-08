package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import simulator.control.Controller;
import simulator.misc.Pair;
import simulator.model.SetContClassEvent;
import simulator.model.Vehicle;

public class ChangeCO2ClassDialog extends JDialog{


	private static final long serialVersionUID = 1L;
	private JComboBox vehicle;
    private JComboBox co2;
    private JSpinner ticks;

    private Controller controller;

    protected ChangeCO2ClassDialog(Controller cont) {
    	super();
    	this.setTitle("Modificacion clase contaminacion");
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
		JTextArea text = new JTextArea("Schedule an event to change the CO2 class of a vehicle after a given number of simulation ticks from now.");
		text.setEditable(false);
		text.setVisible(true);
		text.setLineWrap(true);

		return text;
	}

	private JPanel createCentralPanel(){
    	JPanel centralPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
    	centralPanel.add(vehicleComboBox(this.controller.getVehicles()));
    	centralPanel.add(co2ComboBox());
    	centralPanel.add(ticksSpinner());

    	return centralPanel;
	}

	//vehicle options
	private Component vehicleComboBox (List<Vehicle> vehicles) {
		vehicle = new JComboBox(vehicles.toArray());
		vehicle.setSelectedIndex(0);
		vehicle.setEditable(false);

		return vehicle;
	}

	//co2 class options
	private Component co2ComboBox() {
    	String[] classes = {"0","1","2","3","4","5","6","7","8","9","10"};
		co2 = new JComboBox(classes);
		co2.setSelectedIndex(0);
		co2.setEditable(false);

		return co2;
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
            	List<Pair<String, Integer>> cs = new ArrayList<>();
        		cs.add(new Pair(vehicle.getSelectedItem().toString(), co2.getSelectedItem()));
        		SetContClassEvent event = new SetContClassEvent(controller.getTime() + Integer.parseInt((String)ticks.getValue()), cs);
				controller.addEvent(event);

				setVisible(false);
            }
		});
		return ok;
	}
}
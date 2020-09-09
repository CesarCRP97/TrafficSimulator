package simulator.view;

import java.awt.*;
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
		text.setMinimumSize(new Dimension(300, 100));

		return text;
	}

	private JPanel createCentralPanel(){
    	JPanel centralPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));

    	centralPanel.add(new JTextArea("Vehicle: "));
    	centralPanel.add(vehicleComboBox(this.controller.getVehicles()));

		centralPanel.add(new JTextArea("CO2 class: "));
    	centralPanel.add(co2ComboBox());

    	centralPanel.add(new JTextArea("Ticks: "));
    	centralPanel.add(ticksSpinner());



    	return centralPanel;
	}

	//vehicle options
	private Component vehicleComboBox (List<Vehicle> vehicles) {
		vehicle = new JComboBox(vehicles.toArray());
		vehicle.setEditable(false);
		vehicle.setMinimumSize(new Dimension(300, 40));

		return vehicle;
	}

	//co2 class options
	private Component co2ComboBox() {
    	String[] classes = {"0","1","2","3","4","5","6","7","8","9","10"};
		co2 = new JComboBox(classes);
		co2.setSelectedIndex(0);
		co2.setEditable(false);
		co2.setMinimumSize(new Dimension(300, 40));
		return co2;
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
            	List<Pair<String, Integer>> cs = new ArrayList<>();
        		cs.add(new Pair(vehicle.getSelectedItem().toString(), co2.getSelectedIndex()));
        		SetContClassEvent event = new SetContClassEvent(controller.getTime() + (Integer)ticks.getValue(), cs);
				controller.addEvent(event);

				setVisible(false);
            }
		});
        ok.setMinimumSize(new Dimension(60, 30));
		return ok;
	}
}
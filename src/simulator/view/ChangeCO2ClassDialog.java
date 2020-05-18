package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import simulator.control.Controller;
import simulator.misc.Pair;
import simulator.model.SetWeatherEvent;
import simulator.model.Vehicle;
import simulator.model.Weather;

public class ChangeCO2ClassDialog extends JDialog{


	private static final long serialVersionUID = 1L;
	private JComboBox vehicle;
    private JComboBox co2;
    private JSpinner ticks;
    private Controller controller;
    private JPanel infPanel;
	
    protected ChangeCO2ClassDialog(JFrame panel, Controller cont) {
		controller = cont;
		JPanel co2Panel = new JPanel (new BorderLayout());
		//Mensaje principal.
		co2Panel.add(message(),BorderLayout.NORTH);

		JPanel comboBoxes = new JPanel();
		comboBoxes.setLayout(new BoxLayout(comboBoxes, BoxLayout.LINE_AXIS));

		comboBoxes.add(vehicleComboBox());
		comboBoxes.add(Co2Dialog("Weather: "));
		comboBoxes.add(TicksDialog("Ticks: "),BorderLayout.EAST);

		co2Panel.add(createInferiorPanel(),BorderLayout.SOUTH);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack(); // ajusta la ventana al tama�o de las componentes
		this.setVisible(true);
	}
	
	//vehicle options
	private Component vehicleComboBox (String[] strings) {
		vehicle = new JComboBox(strings);//TO DO poner el array de v1,v
		vehicle.setSelectedIndex(0);
		vehicle.setEditable(false);
		vehicle.addActionListener((ActionListener) this);
		JButton button = new JButton();
		System.out.println(button);
		return vehicle;
	}

	//co2 class options
	private Component Co2Dialog(String string) {
		co2 = new JComboBox<Vehicle>(controller.);//TO DO poner el array de co1
		co2.setSelectedIndex(0);
		co2.setEditable(true);
		co2.addActionListener((ActionListener) this);
		JButton button = new JButton();
		System.out.println(button);
		return co2;
	}

	//ticks options
	private Component TicksDialog (String string) {		
		ticks = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
		JButton button = new JButton();
		System.out.println(button);
		return ticks;
	}

	private Component message() {
    	JTextArea text = new JTextArea("Schedule an event to change the CO2 class of a vehicle after a given number of simulation ticks from now.");
    	text.setEditable(false);
    	text.setVisible(true);
    	text.setLineWrap(true);
		return text;
	}
	
	private Component createInferiorPanel() {
		 infPanel = new JPanel(new FlowLayout());
		 infPanel.add(OkButton("Ok"));
		 infPanel.add(CancelButton("Cancel"));
		 return infPanel;
	}

	private Component CancelButton(String string) {
		JButton cancela = new JButton();
        cancela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {setVisible(false);}
		});
		return null;
	}
    
	//TODO OK BUTTON
	Component OkButton (String string) {
		
		JButton ok = new JButton();
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
        		List<Pair<String, Vehicle>> cs = new ArrayList<>();
        		cs.add(new Pair<String, Vehicle> (vehicle.getSelectedItem().toString(), (Vehicle) co2.getSelectedItem()));
        		try {
						c.addEvent(new SetWeatherEvent((int) ticks.getValue(),cs));
					} catch (Excepciones e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	setVisible(false);
            }
		});
		return ok;
	}
}



















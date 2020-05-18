package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import simulator.misc.Pair;
import simulator.control.Controller;
import simulator.model.Road;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class ChangeWeatherDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private JComboBox<Road> road;
    private JComboBox<Weather> weather;
    private JSpinner ticks;
    private Controller c;
    private JPanel infPanel;
	
	protected ChangeWeatherDialog(JFrame panel, Controller cont) {
		
		JPanel weatherPanel = new JPanel (new BorderLayout());
		
		weatherPanel.add(RoadDialog("Vehicle: "),BorderLayout.WEST);
		weatherPanel.add(WeatherDialog("CO2 Class: "),BorderLayout.CENTER);
		weatherPanel.add(TicksDialog("Ticks: "),BorderLayout.EAST);
		weatherPanel.add(message(),BorderLayout.NORTH);
		weatherPanel.add(createInferiorPanel(),BorderLayout.SOUTH);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack(); // ajusta la ventana al tama�o de las componentes
		this.setVisible(true);
	}
	
	//vehicle options
	private Component RoadDialog (String string) {
		road = new JComboBox<Road>(c.getid());//TODO poner el array de r1,r2...
		road.setSelectedIndex(0);
		road.setEditable(true);
		road.addActionListener((ActionListener) this);
		JButton button = new JButton();
		System.out.println(button);
		return road;
	}

	//co2 class options
	private Component WeatherDialog(String string) {
		weather = new JComboBox<Weather>(c.getid());//TODO poner el array de weathers...
		weather.setSelectedIndex(0);
		weather.setEditable(true);
		weather.addActionListener((ActionListener) this);
		JButton button = new JButton();
		System.out.println(button);
		return weather;
	}

	//ticks options
	private Component TicksDialog (String string) {		
		ticks = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
		JButton button = new JButton();
		System.out.println(button);
		return ticks;
	}

	private Component message() {
		System.out.print("Schedule an event to change the weather of road after a given number of simulation ticks from now.");
		return null;
	}
	
	private Component createInferiorPanel() {
		 infPanel = new JPanel(new FlowLayout());
		 infPanel.add(OkButton("Ok"));
		 infPanel.add(CancelButton("Cancel"));
		 return infPanel;
	}

	private Component CancelButton(String string) {
		JButton cancela = new JButton("Cancel");
        cancela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {setVisible(false);}
		});
		return null;
	}
    
	//TODO OK BUTTON
	Component OkButton (String string) {
		
		JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent action) {
        		List<Pair<String, Weather>> cs = new ArrayList<>();
        		cs.add(new Pair<> (road.getSelectedItem().toString(), (Weather) weather.getSelectedItem()));
        		try {
						c.addEvent(new SetWeatherEvent((int) ticks.getValue(),cs));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	setVisible(false);
            }
		});
		return ok;
	}
}



















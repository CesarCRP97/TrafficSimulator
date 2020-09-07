package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFileChooser fc;

	boolean _stopped;
	
	private Controller _ctrl;
	//Boton carga de archivo
	private AbstractButton load;
	//Boton cambio del nivel de contaminación
	private AbstractButton contClass;
	//Boton para cambiar clima
	private AbstractButton weather;
	//Panel controlador del tiempo
	private AbstractButton run;
	private AbstractButton stop;
	private JSpinner ticks;
	//Boton para salir
	private AbstractButton exit;

	ChangeWeatherDialog w;
	ChangeCO2ClassDialog c;

	//TODO: Cambios estéticos, que el boton exit esté pegado a la derecha, etc...
	protected ControlPanel (Controller cont) {

		_ctrl = cont;
		fc = new JFileChooser();

		//TODO: cambios en los Dialog
		//w = new ChangeWeatherDialog(panel, _ctrl);
		//c = new ChangeCO2ClassDialog(panel, _ctrl);

		JToolBar toolBar = new JToolBar();

		load =  new JButton();
		load.setIcon(new ImageIcon("icons/open.png"));
		toolBar.add(load);
		
		contClass =  new JButton();
		contClass.setIcon(new ImageIcon("icons/co2class.png"));
		toolBar.add(contClass);
		
		weather =  new JButton();
		weather.setIcon(new ImageIcon("icons/weather.png"));
		toolBar.add(weather);
		
		run = new JButton();
		run.setIcon(new ImageIcon("icons/run.png"));
		toolBar.add(run);
		
		stop =new JButton();
		stop.setIcon(new ImageIcon("stop.png"));
		toolBar.add(stop);

		ticks = new JSpinner();
		ticks.setValue(10);
		toolBar.add(ticks);

		exit = new JButton();
		exit.setIcon(new ImageIcon("icons/exit.png"));

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) throws FileNotFoundException {
		if (e.getSource() == exit) {
			System.exit(0);
		}
		if(e.getSource() == this.load) loadFile();

	}


	//Selecciona un archivo mediante un JFileChooser, si no se encuentra el archivo lanza excepción y enseña una ventana de error.
	private void loadFile() throws FileNotFoundException {
		int v = fc.showOpenDialog(null);
		if(v == JFileChooser.APPROVE_OPTION){
			File buf = fc.getSelectedFile();
			if(buf == null){
				JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				FileInputStream file = new FileInputStream(buf);
				_ctrl.reset();
				_ctrl.loadEvents(file);
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void run_sim(int n){
		
		if (n > 0 && !_stopped) {
			try {
				enableToolBar(false);
				_ctrl.run(1, null);
			}
			catch (Exception e) {
				// TODO show error message
				JOptionPane.showMessageDialog(ControlPanel.this, e.getMessage());
				_stopped = true;
				return;
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					run_sim(n - 1);
				}});
			} 
		else {
			enableToolBar(true);
			_stopped = true;
		}
	}

	@SuppressWarnings("unused")
	private void stop() {
		_stopped = true;
	}
	
	private void enableToolBar(boolean enable) {
		if (enable == true) {
			load.setEnabled(enable);
			((AbstractButton) contClass).setEnabled(enable);
			((AbstractButton) weather).setEnabled(enable);
			((AbstractButton) run).setEnabled(enable);
		}
		else {
			load.setEnabled(enable);
			((AbstractButton) contClass).setEnabled(enable);
			((AbstractButton) weather).setEnabled(enable);
			((AbstractButton) run).setEnabled(enable);	
		}
	}
	
	//salida del simuador
	public void ExitButton() {
        setLayout(null);
        _exit = new JButton("Exit");
        _exit.setBounds(300,250,100,30);
        add(_exit);
        _exit.addActionListener((ActionListener) this);
        actionPerformed(null);
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

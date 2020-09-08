package simulator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.swing.*;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements ActionListener{
	
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

		_stopped = false;
		_ctrl = cont;
		fc = new JFileChooser();

		//TODO: cambios en los Dialog
		//w = new ChangeWeatherDialog(panel, _ctrl);
		//c = new ChangeCO2ClassDialog(panel, _ctrl);

		JToolBar toolBar = new JToolBar();

		load =  new JButton();
		load.setIcon(new ImageIcon("resources/icons/open.png"));
		load.addActionListener(this);
		load.setToolTipText("Cargar archivo");
		toolBar.add(load);

		
		contClass =  new JButton();
		contClass.setIcon(new ImageIcon("resources/icons/co2class.png"));
		contClass.addActionListener(this);
		contClass.setToolTipText("Modificar contaminación de vehículo");
		toolBar.add(contClass);
		
		weather =  new JButton();
		weather.setIcon(new ImageIcon("resources/icons/weather.png"));
		weather.addActionListener(this);
		weather.setToolTipText("Cambiar clima de carretera");
		toolBar.add(weather);

		
		run = new JButton();
		run.setIcon(new ImageIcon("resources/icons/run.png"));
		run.addActionListener(this);
		run.setToolTipText("Ejecutar");
		toolBar.add(run);
		
		stop =new JButton();
		stop.setIcon(new ImageIcon("resources/icons/stop.png"));
		stop.addActionListener(this);
		stop.setToolTipText("Parar");
		toolBar.add(stop);

		ticks = new JSpinner();
		ticks.setValue(10);
		toolBar.add(ticks);

		//TODO: Pegar a la derecha.
		exit = new JButton();
		exit.setIcon(new ImageIcon("resources/icons/exit.png"));
		exit.addActionListener(this);
		exit.setToolTipText("Salir");
		toolBar.add(exit);

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(toolBar);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.load) {
			try {
				loadFile();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		else if(e.getSource() == this.weather);//TODO;
		else if(e.getSource() == this.contClass);//TODO;
		else if(e.getSource() == this.run){
			_stopped = false;
			run_sim((Integer) ticks.getValue());
		}
		else if(e.getSource() == this.stop) stop();
		else if (e.getSource() == exit)	System.exit(0);

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

	private void run_sim(int n){
		if (n > 0 && !_stopped) {
			try {
				enableToolBar(false);
				_ctrl.run(1);
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
				}
			});
		}
		else {
			enableToolBar(true);
		}
	}

	private void stop() {
		_stopped = true;
	}
	
	private void enableToolBar(boolean enable) {
		load.setEnabled(enable);
		contClass.setEnabled(enable);
		weather.setEnabled(enable);
		run.setEnabled(enable);
		ticks.setEnabled(enable);
		exit.setEnabled(enable);
	}
}

package simulator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFileChooser fc;
	private JButton _exit;

	JButton _run = new JButton();
	JButton _stopped = new JButton();
	JSpinner ticks = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
	public void FileEvents () {
		this.fc = new JFileChooser();
	}

	private Controller _ctrl;
	
	public void actionPerformed1(ActionEvent e) {
		
		Controller c = new Controller(null, null);
		InputStream i = null;
		
		if (e.getSource() == this.fc) {
			int v = fc.showOpenDialog(null);
			if (v==JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				//si el fichero no existe o loadEvent...
				if (file == null) {
					//throw new IllegalArgumentException("Not valid road parameters");
					JOptionPane.showMessageDialog(null, "FILE DOESN�T EXIST!!", null, JOptionPane.ERROR_MESSAGE);
					// se supone que hay q lanzar una excepcion con un message dialog
				}
				else {
					file.getName();
					c.reset();
					c.loadEvents(i);
				}
			}
		}	
	}
	
	
	
	
	
	

	
	
	//new SpinnerNumberModel(value, min, max, step)
	
	
	private void run_sim(int n){
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
			}
			catch (Exception e) {
				// TODO show error message
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
	
	private void stop() {
		_stopped = true;
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
   public void actionPerformed(ActionEvent e) {
	   if (e.getSource() == _exit) {
		   System.exit(0);
       }
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

package simulator.control;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFileChooser fc;
	
	public void FileEvents () {
		this.fc = new JFileChooser();
		fc.setCurrentDirectory(new File("C:/Users/usuario/Documents/GitHub/TrafficSimulator/resources/icons/open.png"));
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Controller c = new Controller(null, null);
		InputStream i = null;
		
		if (e.getSource() == this.fc) {
			int v = fc.showOpenDialog(null);
			if (v==JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				//si el fichero no existe o loadEvent...
				if (file == null) {
					//throw new IllegalArgumentException("Not valid road parameters");
					JOptionPane.showMessageDialog(null, "FILE DOESN´T EXIST!!", null, JOptionPane.WARNING_MESSAGE);
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

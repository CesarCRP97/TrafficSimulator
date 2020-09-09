package simulator.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import simulator.control.Controller;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controller _ctrl;
	
	public MainWindow(Controller ctrl) {
		super("Traffic Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);

		mainPanel.add(new ControlPanel(_ctrl), BorderLayout.PAGE_START);
		mainPanel.add(new StatusBar(_ctrl),BorderLayout.PAGE_END);
		
		JPanel viewsPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(viewsPanel, BorderLayout.CENTER);
		
		JPanel tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(tablesPanel);
		
		JPanel mapsPanel = new JPanel();
		mapsPanel.setLayout(new BoxLayout(mapsPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(mapsPanel);
		// tables
		
		JPanel eventsView = createViewPanel(new JTable(new EventsTableModel(_ctrl)), "Events");
		eventsView.setMinimumSize(new Dimension(500, 200));
		tablesPanel.add(eventsView);

		JPanel vehiclesView = createViewPanel(new JTable(new VehiclesTableModel(_ctrl)), "Vehicles");
		vehiclesView.setMinimumSize(new Dimension(500, 200));
		tablesPanel.add(vehiclesView);

		JPanel roadsView = createViewPanel(new JTable(new RoadsTableModel(_ctrl)), "Roads");
		roadsView.setMinimumSize(new Dimension(500, 200));
		tablesPanel.add(roadsView);

		JPanel junctionsView = createViewPanel(new JTable(new JunctionsTableModel(_ctrl)), "Junctions");
		junctionsView.setMinimumSize(new Dimension(500, 200));
		tablesPanel.add(junctionsView);

		// maps
		
		JPanel mapView = createViewPanel(new MapComponent(_ctrl), "Map");
		mapView.setPreferredSize(new Dimension(500, 400));
		mapsPanel.add(mapView);

		JPanel mapByRoadView = createViewPanel(new MapByRoadComponent(_ctrl), "Map");
		mapByRoadView.setPreferredSize(new Dimension(500, 400));

		mapsPanel.add(mapByRoadView);


		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel createViewPanel(JComponent c, String title) {
		JPanel p = new JPanel( new BorderLayout() );
		// TODO add a framed border to p with title
		p.add(new JScrollPane(c));
		createBorders(p, title);
		return p;
	}

	private void createBorders(JPanel panel, String title){
		Border b = BorderFactory.createLineBorder(Color.black, 2);
		panel.setBorder(BorderFactory.createTitledBorder(b, title));
	}
}

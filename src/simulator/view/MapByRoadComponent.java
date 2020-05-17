package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.VehicleStatus;

public class MapByRoadComponent extends JComponent implements TrafficSimObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int _JRADIUS = 10;

	private static final Color _BG_COLOR = Color.WHITE;
	private static final Color _JUNCTION_COLOR = Color.BLUE;
	private static final Color _JUNCTION_LABEL_COLOR = new Color(200, 100, 0);
	private static final Color _GREEN_LIGHT_COLOR = Color.GREEN;
	private static final Color _RED_LIGHT_COLOR = Color.RED;
    int i=0;   

	private RoadMap _map;

	private Image _car;
	
	public MapComponent(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}

	private void initGUI() {
		_car = loadImage("car.png");
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		setPreferredSize (new Dimension (300, 200));
		
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// clear with a background color
		g.setColor(_BG_COLOR);
		g.clearRect(0, 0, getWidth(), getHeight());

		if (_map == null || _map.getJunctions().size() == 0) {
			g.setColor(Color.red);
			g.drawString("No map yet!", getWidth() / 2 - 50, getHeight() / 2);
		} else {
			updatePrefferedSize();
			drawMap(g);
		}
	}
	
	private void drawMap(Graphics g) {
		drawRoads(g);
		drawVehicles(g);
		drawJunctions(g);
		drawIcons(g);
	}
	
	private void drawRoads(Graphics g) {
		for (Road r : _map.getRoads()) {
 
			// the road goes from (x1,y1) to (x2,y2)
			int x1 = r.getSrc().getX();
			int y1 = r.getSrc().getY();
			int x2 = r.getDest().getX();
			int y2 = r.getDest().getY();
			
			// choose a color for the road depending on the total contamination, the darker
			// the
			// more contaminated (wrt its co2 limit)
			int roadColorValue = 200 - (int) (200.0 * Math.min(1.0, (double) r.getTotalCont() / (1.0 + (double) r.getTotalCont())));
			Color roadColor = new Color(roadColorValue, roadColorValue, roadColorValue);
			
			//TO DO: poner la i-esima (no se si esta bien????)
			for (Object o : _map.getRoads()) {
				g.drawLine(x1=50, y1=(i+1)*50, x2=getWidth() , y2=(i+1)*50);
			}
			
			Vehicle v ;
			boolean status = v.getStatus() != null;
			if ( status = VehicleStatus.PENDING != null) {
				System.out.println("Pending");
			}else if (status = VehicleStatus.TRAVELING != null) {
				System.out.println("r1:44");
			}else if (status = VehicleStatus.WAITING != null) {
				System.out.println("Wairing:j1");
			}else if (status = VehicleStatus.ARRIVED != null) {
				System.out.println("Arrived");
			}
		}
	}
	
	//TO DO poner green or red al final de las lineas
	private void drawJunctions(Graphics g) {
		for (Junction j : _map.getJunctions()) {

			// (x,y) are the coordinates of the junction
			int x = j.getX();
			int y = j.getY();

			// draw a circle with center at (x,y) with radius _JRADIUS
			g.setColor(_JUNCTION_COLOR);
			g.fillOval(x - _JRADIUS / 2, y - _JRADIUS / 2, _JRADIUS, _JRADIUS);

			// draw the junction's identifier at (x,y)
			g.setColor(_JUNCTION_LABEL_COLOR);
			g.drawString(j.getId(), x, y);
		}
	}
	
	private void drawVehicles(Graphics g) {
		for (Vehicle v : _map.getVehicles()) {
			if (v.getStatus() != VehicleStatus.ARRIVED) {

				// The calculation below compute the coordinate (vX,vY) of the vehicle on the
				// corresponding road. It is calculated relativly to the length of the road, and
				// the location on the vehicle.
				Road r = v.getRoad();
				int x1 = r.getSrc().getX();
				int y1 = r.getSrc().getY();
				int x2 = r.getDest().getX();
				int y2 = r.getDest().getY();
				
				double x = x1 + (int) ((x2 - x1) * ((double) v.getLocation() / (double) r.getLength()));
				double y = 0;
				
				int xDir = x1 < x2 ? 1 : -1;
				int yDir = y1 < y2 ? 1 : -1;

				int vX = x1 + xDir * ((int) x);
				int vY = y1 + yDir * ((int) y);

				// Choose a color for the vehcile's label and background, depending on its
				// contamination class
				int vLabelColor = (int) (25.0 * (10.0 - (double) v.getContClass()));
				g.setColor(new Color(0, vLabelColor, 0));

				// draw an image of a car (with circle as background) and it identifier
				g.fillOval(vX - 1, vY - 6, 16, 16);
				g.drawImage(_car, vX, vY - 6, 12, 12, this);
				g.drawString(v.getId(), vX, vY - 6);
			}
		}
	}
	
	// this method is used to update the preffered and actual size of the component,
	// so when we draw outside the visible area the scrollbars show up
	private void updatePrefferedSize() {
		int maxW = 200;
		int maxH = 200;
		for (Junction j : _map.getJunctions()) {
			maxW = Math.max(maxW, j.getX());
			maxH = Math.max(maxH, j.getY());
		}
		maxW += 20;
		maxH += 20;
		setPreferredSize(new Dimension(maxW, maxH));
		setSize(new Dimension(maxW, maxH));
	}
	
	// loads an image from a file
	private Image loadImage(String img) {
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {}
		
		return i;
	}

	public void update(RoadMap map) {
		_map = map;
		repaint();
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

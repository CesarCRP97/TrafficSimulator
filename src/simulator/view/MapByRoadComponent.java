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
import simulator.model.Weather;

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
	private static final Color _BLACK_LIGHT_COLOR = Color.BLACK;

	private RoadMap _map;

	private Image _car;
	private Image _cont0;
	private Image _cont1;
	private Image _cont2;
	private Image _cont3;
	private Image _cont4;
	private Image _cont5;
	private Image _sun;
	private Image _wind;
	private Image _rain;
	private Image _storm;
	private Image _cloud;


	
	public MapByRoadComponent(Controller ctrl) {
		setPreferredSize (new Dimension (300, 200));
		initGUI();
		ctrl.addObserver(this);
	}

	private void initGUI() {
		_car = loadImage("car.png");
		_cont0 = loadImage("cont_0.png");
		_cont1 = loadImage("cont_1.png");
		_cont2 = loadImage("cont_2.png");
		_cont3 = loadImage("cont_3.png");
		_cont4 = loadImage("cont_4.png");
		_cont5 = loadImage("cont_5.png");
		_rain = loadImage("rain.png");
		_sun = loadImage("sun.png");
		_wind = loadImage("wind.png");
		_storm = loadImage("storm.png");
		_cloud = loadImage("cloud.png");
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
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
	}
	
	private void drawRoads(Graphics g) {

		g.setColor(_BLACK_LIGHT_COLOR);
		for (Road r : _map.getRoads()) {
 
			// the road goes from (x1,y1) to (x2,y2)
			int x1 = 50;
			int y = ((_map.getRoads().indexOf(r)) + 1)*50 	;
			int x2 = getWidth() + 100;


			g.drawLine(x1, y, x2, y);

			x2 += 15 ;
			y -= 16;
			
			if (r.getWeather() == Weather.SUNNY) {
				g.drawImage(_sun, x2, y,32, 32, this);
			}else if (r.getWeather() ==Weather.CLOUDY) {
				g.drawImage(_cloud, x2, y, 32, 32, this);
			}else if (r.getWeather() == Weather.RAINY) {
				g.drawImage(_rain, x2, y, 32, 32, this);
			}else if (r.getWeather() == Weather.WINDY) {
				g.drawImage(_wind,  x2, y,32, 32, this);
			}else if (r.getWeather() == Weather.STORM) {
				g.drawImage(_storm, x2, y, 32, 32, this);
			}

			int c = (int) Math.floor(Math.min((double) r.getTotalCont()/(1.0 + (double) r.getLength()),1.0) / 0.19);

			x2 += 40;

			if ( c == 0) {
				g.drawImage(_cont0, x2, y, 32, 32, this);
			}else if ( c == 1) {
				g.drawImage(_cont1, x2, y, 32, 32, this);
			}else if ( c == 2) {
				g.drawImage(_cont2, x2, y, 32, 32, this);
			}else if ( c == 3) {
				g.drawImage(_cont3, x2 , y, 32, 32, this);
			}else if ( c == 4) {
				g.drawImage(_cont4, x2, y, 32, 32, this);
			}else if ( c == 5) {
				g.drawImage(_cont5, x2, y, 32, 32, this);
			}
		}
	}

	private void drawVehicles(Graphics g) {
		g.setColor(Color.GREEN);
		for (Vehicle v : _map.getVehicles()) {
			if (v.getStatus() != VehicleStatus.ARRIVED) {
				
				Road r = v.getRoad();
				
				int x1 = 50;
				int y = ((_map.getRoads().indexOf(r)) + 1)*50;
				int x2 = getWidth() + 100;
				
				int x =  x1 + (int) ((x2 - x1) * ((double) v.getLocation() / (double) r.getLength()));

				// draw an image of a car (with circle as background) and it identifier
				g.fillOval(x - 1, y-8, 14, 14);
				g.drawImage(_car, x, y-8, 16, 16, this);
				g.drawString(v.getId(), x, y-8);
			}
		}
	}
	
	private void drawJunctions(Graphics g) {
		for (Road r : _map.getRoads()) {

			int x1 = 50;
			int y = ((_map.getRoads().indexOf(r)) + 1)*50;
			int x2 = super.getWidth() + 100;

			g.setColor(_JUNCTION_COLOR);
			g.fillOval( x1 - _JRADIUS / 2, y - _JRADIUS / 2, _JRADIUS, _JRADIUS);

			Color semaforo = _RED_LIGHT_COLOR;
			int idx = r.getDest().getGreenLightIndex();
			if (idx != -1 && r.equals(r.getDest().getInRoads().get(idx))) {
				semaforo = _GREEN_LIGHT_COLOR;
			}
			g.setColor(semaforo);

			g.fillOval( x2 - _JRADIUS / 2, y - _JRADIUS / 2, _JRADIUS, _JRADIUS);


			g.setColor(_JUNCTION_LABEL_COLOR);
			g.drawString(r.getSrc().getId(), x1, y-10);
			
			g.setColor(_JUNCTION_LABEL_COLOR);
			g.drawString(r.getDest().getId(), x2, y-10);
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
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		update(map);
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		update(map);
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		update(map);
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		update(map);
	}

	@Override
	public void onError(String err) {
	}
}

package simulator.model;
import java.util.List;

public class SetContClassEvent extends Event {
	
	private List<Pair<String, Weather>> cs;

	SetContClassEvent(int time, List<Pair<String,Weather>> cs) {
		super(time);
		this.cs=cs;
		// TODO Auto-generated constructor stub
	}
	
    @Override
    void execute(simulator.model.RoadMap map) {

    	for (int c=0; c<cs.size(); c++) {
    		//TODO
    		cs.get(c.getFirst(map));
    		cs.get(c.getSecond(map));
    		//TODO
    	}
    }
}


import java.util.ArrayList;

public class Game {

	public PhysicsSim s;	
	public ArrayList<Object> objects;	
	public long totalElapsedTime;
	public long frame;
	public WorldCreator w;
	public  ArrayList<trackingPoint> trackers;
	//this class needs to be phased out
	public Game(WorldCreator world, ArrayList<Object> obs,ArrayList<trackingPoint> trackers) {
		this.trackers=trackers;
		objects = obs;
		w = world;		
		s = new PhysicsSim(objects, world,trackers);		
	}
}

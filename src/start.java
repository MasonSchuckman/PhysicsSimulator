import java.io.IOException;
import java.util.ArrayList;

import javax.vecmath.Vector3d;

import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;


//this class sets up all the necessary parts of the simulation, then starts the loop
public class start { 
	public static ArrayList<Object> list;
	public static Game game;
	public static WorldCreator uni;
	public static ArrayList<trackingPoint> trackers;
	public static camControls controls; //controls for the camera
	public static playerControls Pcontrols;	

	public static void main(String [] args) throws InvalidKerasConfigurationException, UnsupportedKerasConfigurationException, IOException 	{
		//use presets.<preset here> to make the objects and universe.
		//use .manEdit in to make new presets
		//use .testing in to test various things
		//list=presets.preset6();
		list=presets.manEdit();		
		//System.out.println(list.size()); //prints the particles in the simulation
		
		uni=presets.uni;
		trackers=presets.trackers;
		if(!trackers.isEmpty())uni.turnOnMotionControls(); //if at least 1 light tracker is present, turn on optical motion controls.
		game = new Game(uni, list,trackers);		
		Pcontrols=new playerControls(list,uni.motionControls,uni,trackers);//bool is for motion controls.
		
		sceneSetup simulationRunner=new sceneSetup(); //setup the application
		simulationRunner.startLoop(args); //start the application loop
	}
}

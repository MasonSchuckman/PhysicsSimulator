import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class movementCollector {
	public File file;
	private BufferedReader br;
	private String st;
	private int currentLine = 0;
	public Server s;

	// old class used with "Server" to use data sent from a python server as motion
	// data for the simulation
	// not deleted in case I want to reimplement some features.
	public movementCollector() {// "C:\\Users\\suprm\\Documents\\PhonePi_SampleServer-master\\accelerometer.txt"

	}

	public float[] getData(String data) {
		// String data=s.getData();
		System.out.println(data);
		float[] motion = new float[3];
		String[] splited = data.split(",");

		motion[0] = Float.parseFloat(splited[0]);
		motion[1] = Float.parseFloat(splited[1]);
		motion[2] = Float.parseFloat(splited[2]);
		return motion;
	}

	public float[][] getData(int lines) {
		currentLine += lines;
		float[][] movement = new float[3000][3];
		int c = 0;
		try {
			while ((st = br.readLine()) != null) {
				String[] splited = st.split(",");

				movement[c][0] = Float.parseFloat(splited[0]);
				movement[c][1] = Float.parseFloat(splited[1]);
				movement[c][2] = Float.parseFloat(splited[2]);
				c++;
				if (c > lines)
					break;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movement;
	}

}

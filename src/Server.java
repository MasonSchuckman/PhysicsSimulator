
// A Java program for a Server 
import java.net.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.*;
//old class used to communicate java with python
//currently replaced by light tracking, havn't deleted in case
//I want to re-implement the python motion tracking system.
//(Python server collected motion data and sent it to the java web socket)

public class Server extends Thread{
	// initialize socket and input stream
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;
	private String data;
	private BufferedReader d;
	private Process process;
	private final AtomicBoolean running = new AtomicBoolean(false);
	private playerControls p;
	// constructor with port
	public Server(int port,playerControls play) {
		// starts server and waits for a connection
		p=play;
		try {
			server = new ServerSocket(port);
			System.out.println(server.getLocalSocketAddress());
			System.out.println(server.getLocalPort());
			System.out.println();
			System.out.println("Server started");
			System.out.println(InetAddress.getLocalHost());
			System.out.println("Waiting for a client ...");
			System.out.println(String.valueOf(InetAddress.getLocalHost().getHostAddress()));
			startPythonServer();
			socket = server.accept();
			System.out.println("Client accepted");

			// takes input from the client socket
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			d = new BufferedReader(new InputStreamReader(in));
//			while(true) {
//				p.addData(d.readLine());
//			}
		} catch (IOException i) {
			System.out.println(i);
		}
	}
	public void run() {
//		while(true) {
//		try {
//			//p.addData(d.readLine());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		}
	}
	public void getData() {
//		try {
//			if(d.readLine()!=null)
//			p.addData(d.readLine());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			String dat=d.readLine();
//			System.out.println(dat);
//			return dat;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			//e.printStackTrace();
//		}
//		return "nothing";
	}

	public void closeServer() {
		System.out.println("Closing connection");
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startPythonServer() {
		System.out.println("Starting Python Server...");
		
		String tcp="C:\\Users\\suprm\\Documents\\PhonePi_SampleServer-master\\PhonePiPython3.py";
		String udp="C:\\Users\\suprm\\Documents\\ser.py";
		String udp2="C:\\Users\\suprm\\Desktop\\test.py";
		String path = udp2;
		
		try {
			//Thread.sleep(4000);
			process = Runtime.getRuntime().exec("python " + path);

			//Thread.sleep(1000);
		} catch (IOException e) {
		}
	}

	public void killPythonServer() {
		System.out.println("Killing Python Server...");
		process.destroyForcibly();
	}

}
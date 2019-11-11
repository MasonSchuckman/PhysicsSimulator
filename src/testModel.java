
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
//class for testing the machine learning algorithm

public class testModel {
	private static int lines=0;
	static FileWriter fileWriter;
    static PrintWriter printWriter; 
	public static void main(String[] args) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
		predictor gp = null;
		String tensorflowJniPath = "C:\\Users\\suprm\\Downloads\\libtensorflow_jni-cpu-windows-x86_64-1.14.0\\tensorflow_jni.dll";
		String AIModelPath = "yeah6.h5";
		try {
			gp = new predictor(tensorflowJniPath, AIModelPath);
		} catch (IOException e1) {}
		
		//dims of 1,12,3
		
		String path="C:\\Users\\suprm\\Desktop\\goodDisplacementData.txt";
		String path2="C:\\Users\\suprm\\Desktop\\badDisplacementData.txt";
		String toConvert="C:\\Users\\suprm\\Desktop\\newBadData2.txt";
		String testData="C:\\Users\\suprm\\Desktop\\testData.txt";
		String outputPath="C:\\Users\\suprm\\Desktop\\formattedBadData2.txt";
			//System.out.println(fileToArray(path)[0][0][0]);
		fileWriter = new FileWriter(outputPath);
	    printWriter = new PrintWriter(fileWriter);	    
	    
	    lines=customUtils.getLines(path2)/13;
	    System.out.println(lines);
		float[][][]data=customUtils.fileToArray(path2);
		int counts=0;
		//data=customUtils.setToDisplacement(data,0);	
		//data=customUtils.reverseData(data);
			
		
		//guessing max value of displacement, needed for normalization of data for evaluating machine learning algorithm
		int predictedMax=80; 
		
		for(int k=0; k<data.length; k++) {
			for(int i=0; i<12; i++) {
				//printWriter.print(data[k][i][0]+","+data[k][i][1]+","+data[k][i][2]+"\n");  
				data[k][i]=customUtils.normalizeArray(data[k][i],predictedMax);				
				//System.out.println(data[k][i][0]+","+data[k][i][1]+","+data[k][i][2]);
			}			
			float [][][]a= {data[k]};			
			double confidence=(gp.evalData(a)); //evaluates 1 set of data, returns the confidence of the model
			if(confidence>.5) {
				System.out.print(confidence);
				System.out.print("  set: "+k+"");	
				System.out.print("\n");
				
				counts++;
			}
			//printWriter.print("\n");
		}		
		//printWriter.close();
		System.out.println(counts);
	}
}


import java.io.IOException;


import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.io.ClassPathResource;
import org.nd4j.linalg.util.ArrayUtil;

public class predictor {	
	
	private MultiLayerNetwork model;
	public predictor(String jnipath,String modelName) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
		String simpleMlp = new ClassPathResource(modelName).getFile().getPath();
		//System.out.println(System.getProperty("java.class.path")); //model has to be in this directory		
		model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp); //load the model from the path
		System.load(jnipath);	//load the ini file for tensorflow
        }
    
    public double evalData(float [][][]inputData) 
	{	
    	float[] flat = ArrayUtil.flattenFloatArray(inputData);
    	
    	int[] shape =new int[]{1,12,3};	//Array shape here    	
    	INDArray myArr = Nd4j.create(flat,shape);    	
		double result=model.output(myArr).getDouble(0);
		
    	return result;
    }
}

package td2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.Spring;

public class AppModel {
	private ListWordModel<Definition> lwm;
	public PropertyChangeSupport pcs;
	
	public AppModel(){
		this.lwm = new ListWordModel<Definition>();
		this.pcs = new PropertyChangeSupport(this);
		
	}
	
	public void openDictionnary(File f){
		//parse dictionnary
		try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream(f.toString());
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  int index=0;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
				  //System.out.println (strLine);
				  String[] tmp;
				  tmp=strLine.split(" = ");
				  
				  Definition def = new Definition(tmp[0], tmp[1]);
				  lwm.addElement(def);
				  System.out.println("Adding: " + def.toString());
			  }
			  //Close the input stream
			  in.close();
			  
			  
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		//inject each element in the ListWordModel
		
	}
	
	public void addListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }
	
	public ListWordModel getLwm(){
		return this.lwm;
	}
}

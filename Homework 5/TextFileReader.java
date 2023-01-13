import java.io.*;
public class TextFileReader {
    private BufferedReader in;

public TextFileReader(String filePath) {
    
    /**
     * TextFileReader  
     * @param filename the name of the file to be opened given the location or filePath
     * @throws RuntimeException if the file cannot be opened
     */

    try{
        in = new BufferedReader(new FileReader(filePath));      
    }
    catch (FileNotFoundException e){
            throw new RuntimeException("Could not load the file" + filePath );
            
        }
    }
        
   
            

   	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			throw new RuntimeException("Could not close file");
		}
	}
       
       
	/**
	 * reads one line of the data file,
	 * 
	 * @return the String line from the data file, or null if end-of-file
	 * @throws RuntimeException
	 *             if the file cannot be read
	 */
	public String readLine() {
		try {
			return in.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Cannot read from the file");
		}
	}
       
       
       
      
        
    }
 

 
    

        
        
        


      


       

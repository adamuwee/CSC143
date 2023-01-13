import java.util.StringTokenizer;
import javax.swing.*; 

public class SpellChecker {
    
    // Dictionary File Path                     
    private static String DICTIONARY_FILE = "C:\\Documents and Settings\\Open\\Desktop\\HW 5\\SpellChecker\\WordList140k.txt";
    private static String RESUME_FILE = "C:\\Documents and Settings\\Open\\Desktop\\HW 5\\SpellChecker\\Freddy's resume.txt";
	
	//Dictionary
	private static SimpleSortedSet<String> d;
	
    public SpellChecker(){

    }
    /**
     * @param args[0] The file name represented as a string.
     */
    public static void main(String[] args) {
 
		  // Get Dictionary
		  getDictionary();
		  
		  // Get User File
		  // getUserFile(args[0]);
		  
		  //  Read the File
          TextFileReader r = new TextFileReader(RESUME_FILE);
          String lineResume;
          
          do{
              lineResume = r.readLine();
              if(lineResume == null){
                  break;}
                  // Break up the line of strings
                  String[] temp = lineResume.split(" ");
                  
                  // Check each word in line read.
                  for(int index = 0; index < temp.length; index++) {
                      
                      // The word to check
                      String word = null;
                      
                      // Strip word of punctuation
                      String[] tempPeriod = temp[index].split(".");
                      String[] tempExclamation = tempPeriod[0].split("!");
                      String[] tempComma = tempExclamation[0].split(",");
                      String[] tempSemiColon = tempComma[0].split(";");


                      // Get the First String of the Array  Assuming the string has the word first than the period following
                      word = tempSemiColon[0];
                      
                      // Check the word
                      // >=0 Replace the word
                      // -1 ignore the word
                      // -2 ignore and add to dictionary
                      int operation = spellCheck(word);
                      
                      // Replace the current word
                      if ( operation >=0 ) {
                          // replace the word with the index of the dictionary
                        }
                        
                      // Ignore the word
                      else if ( operation == -1 ) {
                          
                        }
                        
                      // Ignore the word and add it to the dictionary
                      else if ( operation == -2 ) {
                          
                        } 
                    }
              

                }while(lineResume !=null);
    }

    private static void scanDocument(SimpleMap document, SimpleSortedSet dictionary) {    
    }
    
    public static void getDictionary(){
        TextFileReader r = new TextFileReader(DICTIONARY_FILE);
        d = new SimpleSortedSet<String>();
     
        
        String something;
        do{
            something = r.readLine();
            if (something == null){
            break;         }
            d.add(something);         
      
        } while(something != null );
        
        
        for (String s : d){
            System.out.print(s + "\n");
            
        }
        System.out.print("\nThe total number of words is : " + d.size());
     
        
       }
       
    public static void getUserFile(String userFile){
          TextFileReader r = new TextFileReader(userFile);
          SimpleSortedSet<String> d = new SimpleSortedSet<String>();
          
          String resumeEntry;
          String word;
          do{
              resumeEntry = r.readLine();
              
              if (resumeEntry == null){
                  break;
                }
                
              StringTokenizer st = new StringTokenizer(resumeEntry);
              String str;
              while (st.hasMoreTokens()){
                  str = st.nextToken();
                  d.add(str);      
                  }
                
            }  while (r !=null);
            
             
        for (String s : d){
            
            System.out.print (s + "\n");
            
        }
        System.out.print("\nThe total number of words is : " + d.size());
     
        
       }
       
    private static int spellCheck(String word) {
           
           // Search the Dictionary
           if ( searchDictionary(word) ) {
               return -1; // The word is in the dictionary and we will ignore the word.
            }
		   
            else {
		    Object[] options = {"Replace","Ignore","Ignore and Add to Dictionary"};
			Object initial = new String("Replace");
            // Display JOptionPane to user to find whether we should replace, ignore, ignore and add to dictionary.
			int choice = JOptionPane.showOptionDialog(null, "What would you like to do with: " +  word, "SpellChecker", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE , null, options, options[0]); 
				
           // If the word is in the dictionary ignore the word
		   // 0 - Replace
		   // 1 - Ignore the word
		   // 2 - Ignore and add the word
           switch( choice ) {
		   case 0: // Replace
			   return d.getIndexInSortedSet(word);
			   
		   case 1: // Ignore the word completly
			   return -1;
			   
		   case 2: // Ignore the word and add to dictionary
			   return -2;
           }
        }
           // Default is to ignore the word
		   return -1;
        }
        
    /**
    * Search the dictionary using a binary search method.  Return whether or not the word is in the dictionary
    */
    private static boolean searchDictionary(String word) {
         return false;
         
        }
            
    }

import java.util.StringTokenizer;
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{
    private String[] array;
    public Test()
    {
        
    }

    public static void main(String[] args){
        
        String  s = new String ("This is a test. Is it?");
       
//         String[] t =  s.split(" ");
        StringTokenizer st = new StringTokenizer(s);
        String[] array = new String[st.countTokens()];
        for (int i = 0; i <  array.length;i++){ 
                 array[i] = st.nextToken();
            //System.out.println(st.nextToken());
     
    }
    
     print(array);
     
     
    }
        
        
   

    
    public static void print (String[] a){
        
        for (int i = 0; i < a.length ; i++){
            
            System.out.println(a[i]);
              
        }
        System.out.println("number of words in the array: " + a.length);
        
    }
    
}
package structuredemos;

import java.util.*;

public class PropDemo {
 
   public static void main(String args[]) {
      Properties capitals = new Properties();
      Enumeration<Object> states;
      
      String str;
      
      capitals.put("Illinois", "Springfield");
      capitals.put("Missouri", "Jefferson City");
      capitals.put("Washington", "Olympia");
      capitals.put("California", "Sacramento");
      capitals.put("Indiana", "Indianapolis");
 
      // Show all states and capitals in hashtable.
      states = capitals.keys(); // get set-view of keys
//      Iterator itr = states.iterator();
      while(states.hasMoreElements()) {
    	 String state = (String) states.nextElement();
         System.out.println("the capital of "+state+" is "+capitals.getProperty(state));
      }
      System.out.println();
 
      // look for state not in list -- specify default
      str = capitals.getProperty("Florida", "Not Found");
      System.out.println("The capital of Florida is "
          + str + ".");
   }
}

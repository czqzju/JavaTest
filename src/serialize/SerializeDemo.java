package serialize;

import java.io.*;

public class SerializeDemo {
	public static void main(String [] args) throws ClassNotFoundException
	   {
	      employee e = new employee();
	      e.name = "Reyan Ali";
	      e.address = "Phokka Kuan, Ambehta Peer";
	      e.SSN = 11122333;
	      e.number = 101;
	      try
	      {
//	    	 System.out.println(System.getProperty("user.dir"));
	         FileOutputStream fileOut =
	         new FileOutputStream("tmp/employee.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(e);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in tmp/employee.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	      try {
	      FileInputStream fileInputStream = new FileInputStream("tmp/employee.ser");
	      ObjectInputStream in = new ObjectInputStream(fileInputStream);
	      employee rEmployee = (employee) in.readObject();
	      System.out.println(rEmployee.toString());
	      System.out.println(rEmployee.name + "\r"+rEmployee.address+"\r"+rEmployee.number+"\r"+rEmployee.number);
	      }catch(IOException i) {
	    	  i.printStackTrace();
	      }
	   }
}

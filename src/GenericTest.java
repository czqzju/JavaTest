import java.util.*;

import org.omg.CORBA.PUBLIC_MEMBER;
public class GenericTest {
	public static void main(String [] args) {
		List<String> name = new ArrayList<String>();
		List<Integer> num = new ArrayList<Integer>();
		List<Number> numbers = new ArrayList<Number>();
		
		name.add("Hello");
		num.add(123);
		numbers.add(10000);
		
//		getData(name);
//		getData(num);
//		getData(numbers);
		
//		getUperNumber(name);
		getUperNumber(num);
		getUperNumber(numbers);
		

		
	}
	
	public static void getData(List<?> i) {
		System.out.printf("%s", i.get(0));
		System.out.println();
	}
	
	public static void getUperNumber(List<? extends Number> num) {
        System.out.println("data :" + num.get(0));
     }
}

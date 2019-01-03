
public class ExceptionDemos {
	public static void main(String [] args) {
		demo4();
	}
	
	public static void demo1() {
		try {
			int i = 10 / 0;
		}catch(ArithmeticException e){
			e.printStackTrace();
		}
	}
	
	public static void demo2() {
		int [] a = {1, 2, 3};
		try {
			System.out.println(a[2]+' '+a[0]);
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	public static void demo4() {
		int a[] = new int[2];
	    try{
	       System.out.println("Access element three :" + a[3]);
	    }catch(ArrayIndexOutOfBoundsException e){
	       System.out.println("Exception thrown  :" + e);
	    }
	    finally{
	       a[0] = 6;
	       System.out.println("First element value: " +a[0]);
	       System.out.println("The finally statement is executed");
	    }
	}

}

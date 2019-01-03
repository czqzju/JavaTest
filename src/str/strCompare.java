package str;

public class strCompare {
	public static void main(String [] args) {
		String str = "Hello World";
		String ano = "hello world";
		Object strO = str;
		System.out.println(str.compareTo(ano));
		System.out.println(str.compareToIgnoreCase(ano));
		System.out.println(str.compareTo(strO.toString()));
	}
}

public class TestPassByValue {
  public static void main(String[] args) {
    int num1 = 1;
    int num2 = 2;
    
    double n1 = 1.0;
    double n2 = 2.0;
 
    System.out.println("����ǰ num1 ��ֵΪ��" +
                        num1 + " ��num2 ��ֵΪ��" + num2);
 
    // ����swap����
    swap(num1, num2);
    System.out.println("������ num1 ��ֵΪ��" +
                       num1 + " ��num2 ��ֵΪ��" + num2);
    swap(n1, n2);
    System.out.println("n1:"+n1+"n2:"+n2);
    
    
  }
  /** �������������ķ��� */
  public static void swap(int n1, int n2) {
    System.out.println("\t���� swap ����");
    System.out.println("\t\t����ǰ n1 ��ֵΪ��" + n1
                         + "��n2 ��ֵ��" + n2);
    // ���� n1 �� n2��ֵ
    int temp = n1;
    n1 = n2;
    n2 = temp;
 
    System.out.println("\t\t������ n1 ��ֵΪ " + n1
                         + "��n2 ��ֵ��" + n2);
  }
  public static void swap(double num1, double num2) {
	  double tmp = num1;
	  num1 = num2;
	  num2 = tmp;
	}
}
import java.util.Scanner; 
 
public class ScannerDemo {
    public static void main(String[] args) {
        demo4();
    }
    
    public static void demo1() {
    	Scanner scan = new Scanner(System.in);
        // �Ӽ��̽�������
 
        // next��ʽ�����ַ���
        System.out.println("next��ʽ���գ�");
        // �ж��Ƿ�������
        if (scan.hasNext()) {
            String str1 = scan.next();
            System.out.println("���������Ϊ��" + str1);
        }
        scan.close();
    }
    
    public static void demo2() {
    	Scanner scan = new Scanner(System.in);
        // �Ӽ��̽�������
 
        // next��ʽ�����ַ���
        System.out.println("nextLine��ʽ���գ�");
        // �ж��Ƿ�������
        if (scan.hasNextLine()) {
            String str1 = scan.nextLine();
            System.out.println("���������Ϊ��" + str1);
        }
        scan.close();
    }
    
    public static void demo3() {
    	Scanner scan = new Scanner(System.in);
        // �Ӽ��̽�������
        int i = 0;
        float f = 0.0f;
        System.out.print("����������");
        if (scan.hasNextInt()) {
            // �ж�������Ƿ�������
            i = scan.nextInt();
            // ��������
            System.out.println("�������ݣ�" + i);
        } else {
            // ����������Ϣ
            System.out.println("����Ĳ���������");
        }
        System.out.print("����С����");
        if (scan.hasNextFloat()) {
            // �ж�������Ƿ���С��
            f = scan.nextFloat();
            // ����С��
            System.out.println("С�����ݣ�" + f);
        } else {
            // ����������Ϣ
            System.out.println("����Ĳ���С����");
        }
        scan.close();
    }
    
    public static void demo4() {
    	Scanner scan = new Scanner(System.in);
    	 
        double sum = 0;
        int m = 0;
 
        while (scan.hasNextDouble()) {
            double x = scan.nextDouble();
            m += 1;
            sum += x;
        }
 
        System.out.println(m + "�����ĺ�Ϊ" + sum);
        System.out.println(m + "������ƽ��ֵ��" + (sum / m));
        scan.close();
    }
}
class Vehicle {}

public class Car extends Vehicle {
    public static void main(String args[]){
        Vehicle v1 = new Vehicle(); //������
        Vehicle v2 = new Car(); //�����ʵ����������Ϊ������
        Car c1 = new Car();    // ������
//        Car c2 = new Vehicle(); //���ᱨ�������͵�ʵ����������Ϊ������

        //Car�����ࣩ��Vehicle�����ࣩ����, Vehicle�����ࣩ����Car�����ࣩ����
        boolean result1 =  c1 instanceof Vehicle;    // true
        boolean result2 =  c1 instanceof Car;        // true
        boolean result3 =  v1 instanceof Vehicle;    // true
        boolean result4 =  v1 instanceof Car;          // false
        boolean result5 =  v2 instanceof Vehicle;    // true
        boolean result6 =  v2 instanceof Car;          // true

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
        System.out.println(result6);
   }
}
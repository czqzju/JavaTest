class Vehicle {}

public class Car extends Vehicle {
    public static void main(String args[]){
        Vehicle v1 = new Vehicle(); //父类型
        Vehicle v2 = new Car(); //子类的实例可以声明为父类型
        Car c1 = new Car();    // 子类型
//        Car c2 = new Vehicle(); //这句会报错，父类型的实例不能声明为子类型

        //Car（子类）是Vehicle（父类）类型, Vehicle（父类）不是Car（子类）类型
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
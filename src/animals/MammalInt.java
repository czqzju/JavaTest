package animals;

public class MammalInt implements animal {

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("Mammal eats");
	}

	@Override
	public void travel() {
		// TODO Auto-generated method stub
		System.out.println("Mammal travels");

	}
	
	public int noOfLegs(){
		return 0;
	}
	
	public static void main(String [] args) {
		animal a = new MammalInt();
		a.eat();
		a.travel();
	}

}

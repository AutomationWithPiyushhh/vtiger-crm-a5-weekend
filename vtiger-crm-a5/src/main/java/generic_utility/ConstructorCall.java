package generic_utility;

public class ConstructorCall {
	public static void main(String[] args) {
		int a  = 5;
		Practice p = new Practice(a);
		p.add();
	}
}


class Practice{
	int a ;
	
	public Practice(int a) {
		this.a = a;
	} 
	
	public void add() {
		System.out.println(a);
	}
}
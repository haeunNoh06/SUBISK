package test;

class A {
	int Asum;
	
	public A(int Asum) {
		this.Asum = Asum;
	}
	
	void print() {
		System.out.println("합계 : "+this.Asum);
	}
}

class B extends A {
	public B(int Asum) {
		super(Asum);

	}

	int max;
	int Bsum;
	A a = new A(Bsum);
	
	void maxNum() {
		this.max = (Asum > Bsum ? Asum : Bsum);
		System.out.println("최대값 : "+this.max);
	}
}


public class TestInherite extends B{

	public TestInherite(int Asum) {
		super(Asum);
	}

	public static void main(String[] args) {
		
	}

}

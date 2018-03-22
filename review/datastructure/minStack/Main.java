package algo;

public class Main {
    public static void main(String args[]) {
    	StackWithMin s1 = new StackWithMin();
    	s1.push(3);
    	s1.push(5);
    	s1.push(2);
    	s1.push(1);
    	s1.push(4);
    	System.out.println(s1.peek().value + " " + s1.peek().min);
    	s1.pop();
    	System.out.println(s1.peek().value + " " + s1.peek().min);
    	s1.pop();
    	System.out.println(s1.peek().value + " " + s1.peek().min);
    	
    	StackWithMin2 s2 = new StackWithMin2();
    	s2.push(3);
    	s2.push(5);
    	s2.push(2);
    	s2.push(1);
    	s2.push(4);
    	System.out.println(s2.min());
    	s2.pop();
    	System.out.println(s2.min());
    	s2.pop();
    	System.out.println(s2.min());
    	s2.pop();
    	System.out.println(s2.min());
    	s2.pop();
    	System.out.println(s2.min());
    	s2.pop();

    }
}

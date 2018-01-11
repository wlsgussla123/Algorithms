package algo;

import java.io.IOException;
import java.util.Scanner;

class Task {
	private int a,b,c;
	private final Scanner sc = new Scanner(System.in);
	
	private void input() {
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
	}
	
	private void process() {
		if(a == b+c) {
			System.out.println(a+"="+b+"+"+c);
		} else if(a == b-c) {
			System.out.println(a+"="+b+"-"+c);
		} else if(a == b*c) {
			System.out.println(a+"="+b+"*"+c);
		} else if(a == b/c) {
			System.out.println(a+"="+b+"/"+c);
		} else if(a+b == c) {
			System.out.println(a+"+"+b+"="+c);
		} else if(a-b == c) {
			System.out.println(a+"-"+b+"="+c);
		} else if(a*b == c) {
			System.out.println(a+"*"+b+"="+c);
		} else {
			System.out.println(a+"/"+b+"="+c);
		}
	}
	
	public void run() throws IOException {
		input();
		process();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

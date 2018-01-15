package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.management.modelmbean.DescriptorSupport;

class Task {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
//	private void input() throws IOException {
//	}
	
	private int calculator(int num, int disposition) {
		int sum = 0;
		while(num > 0) {
			sum = sum + (num % disposition);
			num = num / disposition;
		}
		return sum;
	}
	
	private void process() {
		int a=0,b=0,c=0; // 각각 10진수, 12진수, 16진수
		for(int i=1000; i<=9999; i++) {
			a = calculator(i, 10);
			b = calculator(i, 12);
			c = calculator(i, 16);
			
			if(a == b && b == c) {
				System.out.println(i);
			}
		}
	}
	
	public void run() throws IOException {
		process();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
	}
	
	private void makeNumber(int n, StringBuffer sb) {
		int num = n;
		while(num>0) {
			int digit = num%10; // 6
			num = num/10; // 25
			sb.insert(0, digit);
		}		
	}
	
	private boolean subSum(StringBuffer sb) {
		int sum = 0;
		for(int i=0; i<sb.length(); i++) {
			sum += sb.charAt(i) - '0';
		}
		sum += Integer.parseInt(sb.toString());
		if(sum == N) return true;
		else return false;
	}
	
	private void process() {
		for(int i=1; i<=N; i++) {
			StringBuffer sb = new StringBuffer();
			makeNumber(i,sb);
			if(subSum(sb)) {
				System.out.println(i);
				return;
			}
 		}
		System.out.println(0);
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

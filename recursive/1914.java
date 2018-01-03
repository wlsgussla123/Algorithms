package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Task {
	private int N;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
	}
	
	private void hanoi(int num, int from, int by, int to) {
		if(num==1) {
			System.out.println(from + " " + to);
		} else {
			hanoi(num-1, from, to, by);
			System.out.println(from + " " + to);
			hanoi(num-1, by, from, to);
		}
	}
	
	public void run() throws IOException {
		input();
		BigInteger answer = new BigInteger("2");
		answer = answer.pow(N).subtract(BigInteger.valueOf(1));
		
		System.out.println(answer);
		
		if(N<=20) {
			hanoi(N, 1, 2, 3);			
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

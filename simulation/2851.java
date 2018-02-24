package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N=10;
	private int[] arr = new int[N];
	private int answer = 0;
	// input
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void calculate(int idx, int sum) {
		if(Math.abs(100 - sum) <= Math.abs(100 - answer)) {
			if(Math.abs(100 - sum) == Math.abs(100 - answer)) {
				answer = answer > sum ? answer : sum;
			} else {
				answer = sum;
			}
			
			if(idx+1 < N) {
				calculate(idx+1, answer + arr[idx+1]);
			}
		}
	}
	
	private void solution() {
		calculate(0, arr[0]);
		System.out.println(answer);
	}

	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

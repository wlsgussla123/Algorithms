package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	// input
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
	}
	
	private boolean isPromising(String s) {
		int num = Integer.parseInt(s);
		if(num <= 1) return false;
		
		for(int i=2; i<num; i++) {
			if(num%i == 0) return false;
		}
		
		return true;
	}
	
	private void backtracking(String s, int len) {
		if(len == N) {
			System.out.println(s);
		} else {
			for(int i=1; i<=9; i++) {
				if(isPromising(s + i)) {
					backtracking(s+i, len+1);
				}
			}
		}
	}
	
	private void solution() {
		for(int i=1; i<=9; i++) {
			if(!isPromising(String.valueOf(i))) continue;
			backtracking(String.valueOf(i), 1);
		}
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

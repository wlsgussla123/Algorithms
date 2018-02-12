package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] cols;
	private int answer = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		cols = new int[N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
	}
	
	private boolean isPromising(int row) {
		for(int i=1; i<row; i++) {
			if(cols[row] == cols[i] || row - i == Math.abs(cols[row] - cols[i])) return false;
		}
		return true;
	}
	
	private void nqueen(int row) {
		if(row == N+1) {
			answer++;
		} else {
			for(int i=1; i<=N; i++) {
				cols[row] = i;
				if(isPromising(row)) nqueen(row+1);
			}
		}
	}

	public void run() throws IOException {
		input();
		nqueen(1);
		System.out.println(answer);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

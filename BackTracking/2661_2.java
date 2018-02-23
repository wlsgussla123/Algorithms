package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private boolean flag = false;
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
		int len = s.length();
		int start = len-1;
		int end = len;
		int loop = len/2;
		
		for(int i=1; i<=loop; i++) {
			if(s.substring(start-i, end-i).equals(s.substring(start, end))) {
				return false;
			}
			start--;
		}
		
		return true;
	}
	
	private void backtracking(String s, int len) {
		if(len == N) {
			flag = true;
			System.out.println(s);
		} else {
			for(int i=1; i<=3; i++) {
				if(flag) return;
				if(isPromising(s + i)) {
					backtracking(s+i, len+1);
				}
			}
		}
	}
	
	private void solution() {
		backtracking("1", 1);
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

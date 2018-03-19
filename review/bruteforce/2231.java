package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int answer = 0;
	private int N;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
	}
	
	private void solution(String number) {
		int sum = Integer.parseInt(number);
		int len = number.length();
		for(int i=0; i<len; i++) {
			sum = sum + (number.charAt(i) -'0');
		}
		if(sum == N) {
			answer = Integer.parseInt(number);
			return;
		}
	}
	
	public void run() throws IOException {
		input();
		for(int i=0; i<N; i++) {
			solution(String.valueOf(i));
			if(answer != 0) break;
		}
		System.out.println(answer);
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간초과
class Task {
	private int X;
	private int answer = 987654321;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		X = Integer.parseInt(st.nextToken());
	}
	
	private void solution(int num, int cnt) {
		if(num == 1) {
			answer = answer < cnt ? answer : cnt;
			return;
		}
		
		if(num%3 == 0) {
			solution(num/3, cnt+1);
		}
		if(num%2 == 0) {
			solution(num/2, cnt+1);
		} 
		if(num-1 >= 1) {
			solution(num-1, cnt+1);
		}
	}
	
	public void run() throws IOException {
		input();
		solution(X,0);
		System.out.println(answer);
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

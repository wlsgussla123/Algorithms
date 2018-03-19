package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[] t;
	private long answer = 0;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		t = new int[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			t[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void solution() {
		long l = 1;
		long r = 1000000000;
		long mid = 0;
		long sum = 0;
		while(l<=r) {
			mid = (l+r)/2;
			sum = 0;
			
			for(int i=0; i<N; i++) {
				if(t[i] > mid) {
					sum += t[i] - mid;
				}
			}
			
			if(sum>=M) {
				answer = answer > mid ? answer : mid;
				l = mid+1;
			} else {
				r = mid-1;
			}
		}
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

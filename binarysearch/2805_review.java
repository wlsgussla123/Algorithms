package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[] a;
	private long answer = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		a = new int[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) a[i] = Integer.parseInt(st.nextToken());
	}

	private void process() {
		long left=0, right=1000000000;
		while(left<=right) {
			long mid = (left + right)/2;
			long sum=0;
			for(int i=0; i<N; i++) {
				if(a[i] > mid) sum += (a[i] - mid);
			}
			
			if(sum>=M) {
				left = mid+1;
				if(mid > answer) answer = mid;
			} else {
				right = mid-1;
			}
		}
		System.out.println(answer);
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

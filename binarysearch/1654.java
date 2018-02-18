package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,K;
	private int[] a;
	private long max = 0;
	private long answer = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		a = new int[K];
		for(int i=0; i<K; i++) {
			st = getStringTokenizer();
			a[i] = Integer.parseInt(st.nextToken());
		}
	}

	private void process() {
		long left=0, right = (long)Math.pow(2, 31)-1;
		while(left<=right) {
			long mid = (left + right) / 2;
			long sum = 0;
			
			for(int i=0; i<K; i++) {
				sum += (a[i]/mid);
			}
			// mid가 크다는 소리
			if(sum < N) {
				right = mid-1;
			} else {
				left = mid+1;
				if(mid > answer) answer = mid;
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

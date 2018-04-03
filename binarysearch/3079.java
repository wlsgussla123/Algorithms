package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private long M;
	private int[] time;
	private final long MAX = (long)1e18; // 10^9 
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		time = new int[N];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		init();
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			time[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private boolean isPromising(long mid) {
		long cnt = 0;
		for(int i=0; i<N; i++) {
			cnt += (mid/time[i]);
		}
		
		if(cnt >= M) return true;
		else return false;
	}
	
	private void solution() throws IOException {
		long left = 0, right = MAX;
		long answer = MAX;
		while(left<=right) {
			long mid = (left + right)/2;
			if(isPromising(mid)) {
				right = mid-1;
				answer = answer > mid ? mid : answer;
			} else {
				left = mid+1;
			}
		}
		bw.write(String.valueOf(answer));
	}

	public void run() throws IOException {
		input();
		solution();
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	new Task().run();
    }
}

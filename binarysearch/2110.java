package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task {
	private int N,C;
	private int[] x;
	private final int MAX = 1000000000;
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		x = new int[N];
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			x[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(x);
	}
	
	private boolean isPromising(int mid) {
		int pos = x[0] + mid;
		int cnt = 1;
		for(int i=1; i<N; i++) {
			if(x[i] >= pos) {
				cnt++;
				pos = x[i] + mid;
			}
		}
		
		if(cnt>=C)
			return true;
		else 
			return false;
	}
	
	private void solution() throws IOException {
		int left = 1, right = MAX;
		int answer = 0;
		while(left<=right) {
			int mid = (left + right)/2;
			if(isPromising(mid)) {
				left = mid+1;
				answer = answer > mid ? answer : mid;
			} else {
				right = mid-1;
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

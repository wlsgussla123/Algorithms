package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private long[] trees;
	private long answer = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trees = new long[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			trees[i] = Long.parseLong(st.nextToken());
		}
	}
	
	private void process() {
		long min = 0, max = 2000000000;
		long sum = 0;
		long mid = 0;
		while(min<=max) {
			mid = (min+max)/2;
			sum = 0;
			for(int i=0; i<N; i++) {
				if(trees[i] > mid) sum += trees[i] - mid;
			}
			if(sum>=M) min = mid+1;
			else max = mid-1;
		}
		System.out.println(max);
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

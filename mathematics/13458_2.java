package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,B,C;
	private int[] A;
	private long sum = 0;
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
		A = new int[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = getStringTokenizer();
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
	}

	private void process() throws IOException {
		for(int i=0; i<N; i++) {
			long num = A[i] - B;
			sum++;
			
			if(num>0) {
				sum += num/(long)C;
				num = num%(long)C;
				if(num!=0) sum++;
			}
		}
		System.out.println(sum);
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

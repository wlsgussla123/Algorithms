package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task {
	private int N,L;
	private int[] leak;
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
		L = Integer.parseInt(st.nextToken());
		leak = new int[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) leak[i] = Integer.parseInt(st.nextToken());
	}
	
	private void process() {
		Arrays.sort(leak);
		int cnt = 1;
		int len = 0;
	
		for(int i=1; i<N; i++) {
			len += leak[i] - leak[i-1];
			if(len >= L) {
				cnt++;
				len = 0;
			}
		}
		
		System.out.println(cnt);
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

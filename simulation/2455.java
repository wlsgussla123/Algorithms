package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private int N=4;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		int cnt = 0;
		int max = 0;
		int descend, ascend;
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			descend = Integer.parseInt(st.nextToken());
			ascend = Integer.parseInt(st.nextToken());
			
			cnt += ascend;
			cnt -= descend;
			if(cnt > max) max = cnt;
		}
		System.out.println(max);
	}
	
	private void process() {
		
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

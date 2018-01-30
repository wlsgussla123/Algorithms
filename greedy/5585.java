package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
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
	}

	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void process() {
		int cnt = 0;
		int changes = 1000-N;
		while(changes>0) {
			if(changes>=500) changes -= 500;
			else if(changes>=100) changes -= 100;
			else if(changes>=50) changes -= 50;
			else if(changes>=10) changes -= 10;
			else if(changes>=5) changes -= 5;
			else if(changes>=1) changes -= 1;
			cnt++;
		}
		System.out.println(cnt);
	}
	
	public void run() throws IOException {
		input();
		process();
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int[] m;
	private int N;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		m = new int[8];
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
	}
	
	private void solution() {
		while(N>0) {
			if(N>=50000) {
				N -= 50000;
				m[0]++;
			} else if(N>=10000) {
				N -= 10000;
				m[1]++;
			} else if(N >= 5000) {
				N -= 5000;
				m[2]++;
			} else if(N >= 1000) {
				N -= 1000;
				m[3]++;
			} else if(N >= 500) {
				N -= 500;
				m[4]++;
			} else if(N >= 100) {
				N -= 100;
				m[5]++;
			} else if(N >= 50) {
				N -= 50;
				m[6]++;
			} else if(N >= 10) {
				N -= 10;
				m[7]++;
			} else {
				N -= 10;
			}
		}
		
		for(int i=0; i<8; i++) {
			System.out.print(m[i] + " ");
		}
		System.out.println();
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		int idx = 1;
		while(idx <= T) {
			input();
			System.out.println("#"+idx);
			solution();
			idx++;
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

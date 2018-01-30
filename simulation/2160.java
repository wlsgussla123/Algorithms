package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private char[][][] map;
	private int answer = 987654321;
	private int a1=0, a2=0;
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
		map = new char[N+1][6][8];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=5; j++) {
				st = getStringTokenizer();
				char[] input = st.nextToken().toCharArray();
				for(int k=1; k<=7; k++) {
					map[i][j][k] = input[k-1];
				}
			}
		}
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void compareMap(int m1, int m2) {
		int cnt = 0;
		for(int i=1; i<=5; i++) {
			for(int j=1; j<=7; j++) {
				if(map[m1][i][j] != map[m2][i][j]) cnt++;
			}
		}
		
		if(answer > cnt) {
			answer = cnt;
			a1 = m1;
			a2 = m2;
		}
	}

	private void process() {
		for(int i=1; i<=N; i++) {
			for(int j=i+1; j<=N; j++) {
				compareMap(i,j);
			}
		}
		if(a1>a2) System.out.println(a2 + " " + a1);
		else System.out.println(a1 + " " + a2);
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

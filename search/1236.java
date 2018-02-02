package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int answer = 0;
	private char[][] map;
	private boolean[] row;
	private boolean[] col;
	private int rCnt = 0;
	private int cCnt = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][M+1];
		row = new boolean[N+1];
		col = new boolean[M+1];
		rCnt = N;
		cCnt = M;
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1];
			}
		}
	}
	
	private void process() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] == 'X') {
					if(!row[i]) {
						rCnt--;
						row[i] = true;
					} 
					if(!col[j]) {
						cCnt--;
						col[j] = true;
					}
				}
			}
		}
		
		System.out.println(rCnt > cCnt ? rCnt : cCnt);
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

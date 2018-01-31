package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] map;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private int findMaxIndex(int[] numbers) {
		int max = 0;
		for(int i=1; i<=N; i++) {
			if(numbers[max] <= numbers[i])
				max = i;
		}
		return max;
	}

	private void process() throws IOException {
		int cnt=1;
		int[] index = new int[N+1];
		for(int i=1; i<=N; i++) index[i] = N;
		int[] numbers = new int[N+1];
		for(int i=1; i<=N; i++) numbers[i] = map[N][i];
		int answer = 0;
		int max = 0;
		
		while(cnt!=N) {
			max = findMaxIndex(numbers);
			numbers[max] = map[--index[max]][max];
			cnt++;
		}
		answer = findMaxIndex(numbers);
		System.out.println(numbers[answer]);
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

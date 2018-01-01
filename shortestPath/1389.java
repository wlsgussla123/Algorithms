package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int[][] r;
	private int N, M;
	private final int MAX = 987654321;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		r = new int[N+1][N+1];
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				r[i][j] = MAX;
			}
			r[i][i] = 0;
		}
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		
		int A,B;
		for(int i=0; i<M; i++) {
			st = getStringTokenizer();
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			r[A][B] = 1;
			r[B][A] = 1;
		}
	}
	
	private void floydWarshall() {
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(r[i][j] > r[i][k] + r[k][j]) {
						r[i][j] = r[i][k] + r[k][j];
					}
				}
			}
		}
	}
	
	private void answer() {
		int min = MAX;
		int minIndex = 0;
		int sum = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				sum += r[i][j];
			}
			if(min > sum) {
				min = sum;
				minIndex = i;
			}
			sum = 0;
		}
		
		System.out.println(minIndex);
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(r[i][j]);
			}
			System.out.println();
		}
	}
	
	public void run() throws IOException {
		input();
		floydWarshall();
		answer();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private int[][] map;
	private final int N=100;
	private int answer;
	private final Scanner sc = new Scanner(System.in);
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N][N];
		answer = 0;
	}
	
	private void input() throws IOException {
		init();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
	}
	
	private void solution() {
		int[] sum = new int[2];
		int[] diagonalLine = new int[2];
		int rowcolMax = 0;
		int diagonalMax = 0;
		for(int i=0; i<N; i++) {
			sum[0] = 0;
			sum[1] = 0;
			for(int j=0; j<N; j++) {
				sum[0] += map[i][j];
				sum[1] += map[j][i];
			}
			rowcolMax = Math.max(sum[0], sum[1]);
			answer = Math.max(answer, rowcolMax);
			diagonalLine[0] += map[i][i];
			diagonalLine[1] += map[i][99-i];
		}
		diagonalMax = Math.max(diagonalLine[0], diagonalLine[1]);
		answer = Math.max(answer, diagonalMax);
	}
	
	public void run() throws IOException {
		int idx = 1;
		while(sc.hasNext()) {
			idx = sc.nextInt();
			input();
			solution();
			System.out.println("#"+idx+" " +answer);
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

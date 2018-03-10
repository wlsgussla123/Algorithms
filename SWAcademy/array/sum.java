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
		int sum = 0;
		for(int i=0; i<N; i++) {
			sum = 0;
			for(int j=0; j<N; j++) {
				sum += map[i][j];
			}
			if(sum > answer) answer = sum;
		}
		
		for(int i=0; i<N; i++) {
			sum = 0;
			for(int j=0; j<N; j++) {
				sum += map[j][i];
			}
			if(sum > answer) answer = sum;
		}
		
		int i=0;
		int j=0;
		sum = 0;
		while(i>=0 && i<N && j>=0 && j<N) {
			sum += map[i++][j++];
		}
		if(sum > answer) answer = sum;
		
		i=N-1;
		j=N-1;
		sum = 0;
		while(i>=0 && i<N && j>=0 && j<N) {
			sum += map[i--][j--];
		}
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

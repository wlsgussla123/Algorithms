
package algo;

import java.util.Arrays;
import java.util.Scanner;

class Task {
	private int[][] friends;
	private int N;
	private int M;
	private final Scanner sc = new Scanner(System.in);
	private final int INF = 987654321;
	
	private void input() {
		N = sc.nextInt();
		M = sc.nextInt();
		friends = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==j) friends[i][j] = 0;
				else friends[i][j] = INF;
			}
		}
				
		for(int i=0; i<M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			friends[from][to] = 1;
			friends[to][from] = 1;
		}
	}
	
	private void floydWarshall() {
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				for(int k=1; k<=N; k++)
					if(friends[i][k] + friends[k][j] < friends[i][j]) 
						friends[i][j] = friends[i][k] + friends[k][j];
	}
	
	private void print() {
		int minVal = INF;
		int minNum = 0;
		
		for(int i=1; i<=N; i++) {
			int sum = 0;
			for(int j=1; j<=N; j++) {
				if(friends[i][j] != INF) sum += friends[i][j];
			}
			
			if(sum < minVal) {
				minVal = sum;
				minNum = i;
			}
		}
		
		System.out.println(minNum);
	}
	
	public void run() {
		input();
		floydWarshall();
		print();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}

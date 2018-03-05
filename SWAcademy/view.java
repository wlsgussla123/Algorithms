package algo;

import java.io.IOException;
import java.util.Scanner;

class Task {
	private int[][] map;
	private int N;
	private int[] answer;
	private int sum;
	private Scanner sc = new Scanner(System.in);
	
	private void init() {
		map = new int[1001][1001];
		answer = new int[1001];
		sum = 0;
	}
	
	private void input() {
		N = sc.nextInt();
		init();
		for(int i=1; i<=N; i++) {
			int height = sc.nextInt();
			for(int j=1000; j>1000-height; j--) {
				map[j][i] = 1;
			}
		}
	}
	
	private void solution() {
		// 아파트 단지 (열)
		for(int i=3; i<=N-2; i++) {
			int j=1000; // 아파트 층
			int cnt = 0;
			// 층이 존재한다며 
			while(map[j][i] == 1) {
				int a = 0;
				int b = 0;
				
				// 층이 존재할 때 오른쪽 조망권
				for(int k=i+1; k<=i+2; k++) {
					if(map[j][k] == 0) {
						a++;
					}
				}
				// 층이 존재할 떄 왼쪽 조망권
				for(int k=i-1; k>=i-2; k--) {
					if(map[j][k] == 0) {
						b++;
					}
				}
				// 양 옆으로 조망권 확호하면
				if(a == 2 && b == 2) {
					cnt++;
				}
				j--;
			}
			answer[i] = cnt;
		}
		
		for(int i=3; i<=N-2; i++) {
			sum += answer[i];
		}
	}
	
	public void run() throws IOException {
		int T = 10;
		int idx = 1;
		while(idx<=T) {
			input();
			solution();
			System.out.println("#"+idx+" " + sum);
			idx++;
		}
	}
}
 
public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

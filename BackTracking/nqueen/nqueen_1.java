package algo;

import java.io.IOException;
import java.util.Scanner;

class Task {
	private int N;
	private boolean[] cols, inc, dec;
	private int answer = 0;
	
	private final Scanner sc = new Scanner(System.in);
	
	private void init() {
		cols = new boolean[N+1];
		inc = new boolean[N*2+1];
		dec = new boolean[N*2+1];
	}
	
	private void input() {
		N = sc.nextInt();
		init();
	}
	
	// nqueen function이 하나의 행에 해당.
	private void nqueen(int row) {
		// 모든 행에 퀸을 하나씩 두었으므로
		if(row>N) {
			answer++;
			return;
		}
		// 모든 열탐색
		for(int i=1; i<=N; i++) {
			// 같은 열이 아니고, 증가하는 대각선에 해당 안 하고, 감소 대각선에도 해당하지 않을 때.
			if(!cols[i] && !inc[row+i] && !dec[N+(row-i)+1]) {
				cols[i] = inc[row+i] = dec[N+(row-i)+1] = true;
				nqueen(row+1);
				cols[i] = inc[row+i] = dec[N+(row-i)+1] = false;
			}
		}
	}

	public void run() throws IOException {
		input();
		nqueen(1);
		System.out.println(answer);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

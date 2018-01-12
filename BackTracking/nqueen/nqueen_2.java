package algo;

import java.io.IOException;
import java.util.Scanner;

class Task {
	private int N;
	private int[] cols;
	private int answer = 0;
	
	private final Scanner sc = new Scanner(System.in);
	
	private void init() {
		cols = new int[N+1];
	}
	
	private void input() {
		N = sc.nextInt();
		init();
	}
	
	private boolean promising(int row) {
		for(int i=1; i<row; i++) {
			// 첫번째 행부터 지금 퀸을 냅두기 전까지 돌면서 같은 열에 둔 것이 있는지, 혹은 대각에 해당하는 것이 있는지 확인
			if((cols[row] == cols[i]) || row - i == Math.abs(cols[row] - cols[i]))
				return false;
		}
		return true;
	}
	
	/*
	 * 1. 첫 번째 행, 첫 번째 열에 퀸을 놓는다.
	 * 2. 다음 행에서 간으한 가장 왼쪽 열에 퀸을 놓는다.
	 * 3. n번째 열에 더 이상 퀸을 놓을 수 없다면 backtracking
	 * 4. 마지막 행에 퀸을 놓으면 하나의 해를 구한 것.
	 * 5. 모든 경우의 수를 조사할 때까지 백트랙킹을 해가며 해들을 구하자.
	 */
	private void nqueen(int row) {
		// row가 N이면 queen을 각 행마다 세웠으므로 경우의수 +1
		if(row > N) {
			answer++;
		} else {
			// row가 N이 아니라면, row값에 해당하는 행의 어떤 열에다가 퀸을 두었는지 저장
			for(int i=1; i<=N; i++) {
				cols[row] = i; // row에 열(i)를 저장한다. (가능한 가장 왼쪽 열에 놓는다.)
				if(promising(row)) {
					// 그 행에 다가둘 수 있다면 다음 행으로 넘어간다.
					nqueen(row+1);
				}
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

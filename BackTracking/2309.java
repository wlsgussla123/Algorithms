package algo;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 작성자 : 박진현
 * 문제 : 백준 2309번, 일곱 난쟁이
 * 접근법 : 백트랙킹을 이용하여... 합이 100일때 까지 한다.
 * 내 생각에는 다른 쉬운 접근법이 있을 것 같다.. 다른 것도 참조해봐야겠다
 */

class Task {
	private final Scanner sc = new Scanner(System.in);
	private int N = 9;
	private int[] dwarf = new int[N];
	private int[] visit = {0, 0, 0, 0, 0, 0, 0, 0, 0};
	private int sum = 0;
	private boolean checkAnswer = false;
	
	private void input() {
		for(int i=0; i<N; i++) {
			dwarf[i] = sc.nextInt();
		}
		
		// 오름차순으로 출력해야 하기 때문에 미리 정렬
		Arrays.sort(dwarf);
	}
	
	private void process() {
		int cnt = 1;
		
		for(int i=0; i<N; i++) {
			visit[i] = 1;
			backtracking(i, cnt);
			visit[i] = 0;
		}
		
		if(!checkAnswer) {
			System.out.println("-1");
		}
	}
	
	// 백트랙킹을 이용하여 값이 100이 될 때까지...
	private void backtracking(int index, int cnt) {
		if(cnt == 7) {
			if(checkSize()) {
				checkAnswer = true;
				printAnswer();
			} else {
			}
		} else {
			for(int i = index+1; i<N; i++) {
				visit[i] = 1;
				backtracking(i, cnt+1);
				visit[i] = 0;
			}
		}
	}
	
	// 키를 세봅시다
	private boolean checkSize() {
		for(int i=0; i<N; i++) {
			if(visit[i] == 1) {
				sum += dwarf[i];
			}
		}
		
		if(sum == 100) {
			return true;
		} else {
			sum = 0;
			return false;
		}
	}
	
	// 답 출력
	private void printAnswer() {
		for(int i=0; i<N; i++) {
			if(visit[i] == 1) {
				System.out.println(dwarf[i]);
			}
		}
	}
	
	public void run() {
		input();
		process();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}

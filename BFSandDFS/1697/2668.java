package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] numbers;
	private boolean[] visited;
	private int[] check;
	private int answerCnt = 0;
	private int[] answerArr;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		numbers = new int[N+1];
		visited = new boolean[N+1];
		answerArr = new int[N+1];
		check = new int[N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			numbers[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	// 두 번 방문해서 2가 되면 사이클 존재.
	private void cycle(int index) {
		if(check[index] == 2) return;
		check[index]++;
		cycle(numbers[index]);
	}
	
	// 사이클 아니면 무의미 하므로 0으로.
	private void clear() {
		for(int i=1; i<=N; i++) {
			if(check[i] != 2) check[i] = 0;
		}
	}
	
	private void getAnswer() {
		int cnt = 0;
		// 사이클 존재하는 개수
		for(int i=1; i<=N; i++) {
			if(check[i] == 2) {
				cnt++;
				answerArr[i] = i;
			}
		}
		System.out.println(cnt);
		// 사이클에 해당하는 번호
		for(int i=1; i<=N; i++) {
			if(answerArr[i] != 0) System.out.println(answerArr[i]);
		}
	}

	public void run() throws IOException {
		input();
		for(int i=1; i<=N; i++) {
			cycle(i);
			clear();
		}
		getAnswer();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

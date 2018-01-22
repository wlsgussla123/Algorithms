package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,K;
	private int[] numbers;
	private int cnt = 0;
	private int answer = 0;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		numbers = new int[N+1];
		for(int i=0; i<=N; i++) numbers[i] = i;
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
	}
	
	private void Eratos() {
		boolean hasAnswer = false;
		int quotient = 0;
		for(int i=2; i<=N; i++) {
			if(hasAnswer) break;
			if(numbers[i] != -1) {
				quotient = numbers[i];
			}
			
			for(int j=2; j<=N; j++) {
				if(numbers[j] == -1) continue;
				if(numbers[j] % quotient == 0) {
					cnt++;
					if(cnt == K) {
						answer = numbers[j];
						hasAnswer = true;
					}
					numbers[j] = -1;
				}
			}
		}
	}
	
	public void run() throws IOException {
		input();
		Eratos();
		System.out.println(answer);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

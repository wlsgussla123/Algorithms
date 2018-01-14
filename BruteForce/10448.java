package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private int T, K;
	private int[] numbers;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		numbers = new int[101];
		for(int i=1; i<=100; i++) {
			numbers[i] = i * (i+1)/2;
		}
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		K = Integer.parseInt(st.nextToken());
	}
	
	private boolean process() {
		for(int i=1; i<=100; i++) {
			for(int j=1; j<=100; j++) {
				for(int k=1; k<=100; k++) {
					if(numbers[i] + numbers[j] + numbers[k] == K) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		T = Integer.parseInt(st.nextToken());
		while(T-- > 0) {
			init();
			input();
			if(process()) System.out.println("1");
			else System.out.println("0");
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

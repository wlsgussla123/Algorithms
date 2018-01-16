package algo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
	}
	
	private void process() {
		int answer = 0;
		int num = N;
		int o; // 일의자리
		int t; // 십의자리
		int no; // 다음 일의자리
		int nt; // 다음 십의자리
		
		while(num != N || answer == 0) {
			t = num / 10; // 2
			o = num % 10; // 6
			no = t+o; // 8
			nt = o; // 6
			
			num = nt * 10 + no % 10;
			answer++;
		}
		
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		process();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int L,A,B,C,D; // 방학일수, 국어,수학 분량, 국어,수학 하루 할 수 있는 양
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private int answer = 0;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		L = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		A = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		B = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		C = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		D = Integer.parseInt(st.nextToken());
	}
	
	private void process() {
		for(int i=0; i<L; i++) {
			if(A<=0 && B<=0) {
				answer++;
			} else {
				A -= C;
				B -= D;
			}
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

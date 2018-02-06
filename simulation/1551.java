package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,K;
	private int[] numbers;
	private int[] answer;
	private int len = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer(String token) throws IOException {
		return new StringTokenizer(br.readLine(), token);
	}
	
	private void input() throws IOException {
		st = getStringTokenizer(" ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		answer = new int[N];
		
		st = getStringTokenizer(",");
		while(st.hasMoreTokens()) {
			numbers[len] = Integer.parseInt(st.nextToken());
			answer[len] = numbers[len];
			len++;
		}
	}
	
	private void copy() {
		for(int i=0; i<len; i++) numbers[i] = answer[i];
	}
	
	private void process() throws IOException {
		while(K-->0) {
			for(int i=0; i<len-1; i++) {
				answer[i] = numbers[i+1] - numbers[i];
			}
			len--;
			copy();
		}
		for(int i=0; i<len; i++) {
			if(i!=len-1) System.out.print(answer[i]+",");
			else System.out.println(answer[i]);
		}
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

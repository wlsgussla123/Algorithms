package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private char[] answer;
	private int[] sum = new int[3];
	private char[] a1 = {'A','B','C'};
	private char[] a2 = {'B','A','B','C'};
	private char[] a3 = {'C','C','A','A','B','B'};
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		answer = new char[N];
		st = getStringTokenizer();
		char[] input = st.nextToken().toCharArray();
		for(int i=0; i<N; i++) {
			answer[i] = input[i];
		}
	}
	
	private void getAnswer() {
		int max = 0;
		for(int i=0; i<3; i++) {
			if(sum[max] < sum[i]) max = i;
		}
		System.out.println(sum[max]);
		for(int i=0; i<3; i++) {
			if(sum[max] == sum[i]) {
				switch(i) {
				case 0:
					System.out.println("Adrian");
					break;
				case 1:
					System.out.println("Bruno");
					break;
				case 2:
					System.out.println("Goran");
					break;
				}
			}
		}
	}

	private void process() throws IOException {
		int index = 0;
		for(int i=0; i<answer.length; i++) {
			index = index%3;
			if(answer[i] == a1[index]) sum[0]++;
			index++;
		}
		
		index = 0;
		for(int i=0; i<answer.length; i++) {
			index = index%4;
			if(answer[i] == a2[index]) sum[1]++;
			index++;
		}
		
		index = 0;
		for(int i=0; i<answer.length; i++) {
			index = index%6;
			if(answer[i] == a3[index]) sum[2]++;
			index++;
		}
		getAnswer();
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

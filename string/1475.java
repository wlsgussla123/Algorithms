package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int[] numbers;
	private char[] input;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		input = st.nextToken().toCharArray();
		numbers = new int[10];
	}

	private void process() throws IOException {
		for(int i=0; i<input.length; i++) {
			int num = input[i] - '0';
			numbers[num]++;
		}
		
		int max = numbers[0];
		for(int i=1; i<10; i++) {
			if(i==6 || i==9) continue;
			max = Math.max(max, numbers[i]);
		}
		max = Math.max(max, (numbers[6]+numbers[9]+1)/2);
		System.out.println(max);
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

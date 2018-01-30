package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int[] number = new int[10];
	private int answer = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		char[] input = st.nextToken().toCharArray();
		answer = input.length;
		for(int i=0; i<input.length; i++) {
			switch(input[i]) {
			case 'A':
			case 'B':
			case 'C':
				number[2]++;
				break;
			case 'D':
			case 'E':
			case 'F':
				number[3]++;
				break;
			case 'G':
			case 'H':
			case 'I':
				number[4]++;
				break;
			case 'J':
			case 'K':
			case 'L':
				number[5]++;
				break;
			case 'M':
			case 'N':
			case 'O':
				number[6]++;
				break;
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
				number[7]++;
				break;
			case 'T':
			case 'U':
			case 'V':
				number[8]++;
				break;
			case 'W':
			case 'X':
			case 'Y':
			case 'Z':	
				number[9]++;
				break;
			}
		}
	}
	
	private void process() {
		for(int i=2; i<=9; i++) {
			answer += i*number[i];
		}
		System.out.println(answer);
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}

	public void run() throws IOException {
		input();
		process();
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

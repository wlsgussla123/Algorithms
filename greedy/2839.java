package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
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
	}
	
	private void process() throws IOException {
		int five = N/5, three = 0;
		int num = N%5;
		int answer = -1;
		
		if(num==0) {
			System.out.println(five);
			return;
		}
		
		while(num>0 && five>=0) {
			if(num%3 == 0) {
				three = num/3;
				answer = three + five;
				break;
			} else {
				five--;
				num+=5;
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

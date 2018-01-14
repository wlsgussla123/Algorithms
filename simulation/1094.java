package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int X;
	private int[] num = new int[7];
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		X = Integer.parseInt(st.nextToken());
	}
	
	private void process() {
		boolean flag = true;
		int length = 64;
		int cnt = 0;
		
		while(flag) {
			if(length > X) {
				length = length / 2;
			} else if(length < X) {
				X -= length;
				cnt++;
			} else {
				cnt++;
				System.out.println(cnt);
				flag = false;
			}
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

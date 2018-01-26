package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private char[][] map = new char[8][8];
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		for(int i=0; i<8; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=0; j<8; j++) {
				map[i][j] = input[j];
			}
		}
	}
	
	private void process() {
		int answer = 0;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if((i+j)%2==0 && map[i][j] == 'F') {
					answer++;
				}
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

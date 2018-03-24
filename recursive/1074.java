package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private int N,R,C;
	private int answer;
	private final Scanner sc = new Scanner(System.in);
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = (int)Math.pow(2, N);
	}
	
	private void solution(int N, int x, int y, int jump) throws IOException {
		if(x == R && y == C) {
			bw.write(String.valueOf(answer));
			return;
		}
		
		if((x <= R && R < x + jump) && (y <= C && C < y + jump)) {
			solution(N/2, x, y, jump/2);
			solution(N/2, x, y + jump/2, jump/2);
			solution(N/2, x + jump/2, y, jump/2);
			solution(N/2, x + jump/2, y + jump/2, jump/2);
		} else {
			answer += jump * jump;
		}
	}
	
	public void run() throws IOException {
		input();
		solution(N,0,0,N);
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

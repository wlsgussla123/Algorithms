package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int A,B,C;
	private int min=987654321, max=-1;
	private int[] visited = new int[101];
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		for(int i=0; i<3; i++) {
			st = getStringTokenizer();
			int arrive = Integer.parseInt(st.nextToken());
			int leave = Integer.parseInt(st.nextToken());
			if(min > arrive) min = arrive;
			if(max < leave) max = leave;
			// 사이 시간을 표현하기 위하여 <= 로 하지 않는다.
			for(int j=arrive; j<leave; j++) visited[j]++; 
		}
	}
	
	private void process() throws IOException {
		int answer = 0;
		for(int i=min; i<=max; i++) {
			switch(visited[i]) {
			case 1:
				answer += visited[i] * A;
				break;
			case 2:
				answer += visited[i] * B;
				break;
			case 3:
				answer += visited[i] * C;
				break;
			}
		}
		bw.write(String.valueOf(answer));
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

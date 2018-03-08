package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int[] cost;
	private int[] plan;
	private int answer;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		cost = new int[4];
		plan = new int[13];
		answer = 1<<30;
	}
	
	private void input() throws IOException {
		init();
		st = getStringTokenizer();
		for(int i=0; i<4; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		st = getStringTokenizer();
		for(int i=1; i<=12; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void solution(int month, int total) {
		if(month >= 12) {
			answer = answer > total ? total : answer;
			return;
		}
		
		solution(month+1, total + cost[0] * plan[month+1]);
		solution(month+1, total + cost[1]);
		solution(month+3, total + cost[2]);
		solution(month+12, total + cost[3]);
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		int idx = 1;
		while(idx<=T) {
			input();
			solution(0,0);
			System.out.println("#"+idx+" "+answer);
			idx++;
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

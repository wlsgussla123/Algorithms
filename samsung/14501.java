package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Schedule {
	int t;
	int p;
	public Schedule(int t, int p) {
		this.t = t;
		this.p = p;
	}
}

class Task {
	private int N;
	private Schedule[] schedules;
	private int answer = 0;
	private StringTokenizer st = null;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		schedules = new Schedule[N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			schedules[i] = new Schedule(t, p);
		}
	}
	
	private void dfs(int sum, int day) {
		answer = answer > sum ? answer : sum;
		
		for(int i=day; i<=N; i++) {
			int t = schedules[i].t;
			int p = schedules[i].p;
			if(t+i>N+1) continue;
			dfs(sum + p, t+i);
		}
	}
	
	private void solution() {
		for(int i=1; i<=N; i++) {
			int t = schedules[i].t;
			int p = schedules[i].p;
			if(t+i>N+1) continue;
			dfs(p, t+i);
		}
	}
	
	public void run() throws IOException {
		input();
		solution();
		System.out.println(answer);
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

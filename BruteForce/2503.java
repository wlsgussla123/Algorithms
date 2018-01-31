package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class BaseBall {
	int num;
	int strike;
	int ball;
	public BaseBall(int n, int s, int b) {
		this.num = n;
		this.strike = s;
		this.ball = b;
	}
	
	public int getFirst() {
		return (num/100)%10;
	}
	public int getSecond() {
		return (num/10)%10;
	}
	public int getThird() {
		return num%10;
	}
}

class Task {
	private int N;
	private BaseBall[] rules;
	private boolean[] visited;
	private int answer = 0;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		rules = new BaseBall[N];
		visited = new boolean[10];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			int num = Integer.parseInt(st.nextToken());
			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());
			rules[i] = new BaseBall(num, strike, ball);
		}
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void baseball(String n) {
		char[] num = n.toCharArray();
		boolean flag = true;
		int s=0,b=0;
		for(int i=0; i<N; i++) {
			s=0; b=0;
			BaseBall rule = rules[i];
			if(rule.getFirst() == num[0]-'0') s++;
			if(rule.getSecond() == num[1]-'0') s++;
			if(rule.getThird() == num[2]-'0') s++;
			if(rule.getSecond() == num[0]-'0' || rule.getThird() == num[0]-'0') b++;
			if(rule.getFirst() == num[1]-'0' || rule.getThird() == num[1]-'0') b++;
			if(rule.getFirst() == num[2]-'0' || rule.getSecond() == num[2]-'0') b++;
			if(rule.strike != s || rule.ball != b) {
				flag = false;
				break;
			}
		}
		
		if(flag) answer++;
	}
	
	private void dfs(String num, int cnt) {
		if(cnt==3) {
			baseball(num);
			return;
		} else {
			for(int i=1; i<=9; i++) {
				if(!visited[i] ) {
					visited[i] = true;
					dfs(num+i, cnt+1);
					visited[i] = false;
				}
			}
		}
	}
	
	private void process() {
		for(int i=1; i<=9; i++) {
			visited[i] = true;
			dfs(Integer.toString(i), 1);
			visited[i] = false;
		}
		System.out.println(answer);
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

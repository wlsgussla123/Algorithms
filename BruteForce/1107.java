package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private boolean[] outoforder = new boolean[10];
	private int len = 0;
	private int answer = 987654321;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		len = String.valueOf(N).length();
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		if(M>0) {
			st = getStringTokenizer();
			for(int i=0; i<M; i++) {
				int num = Integer.parseInt(st.nextToken());
				outoforder[num] = true;
			}
		}
	}
	
	private void findChannel(int channel, int cnt) {
		if(channel > N) {
			int sum = cnt + channel - N;
			if(answer > sum) answer = sum;
		} else if (channel < N) {
			int sum = cnt + N - channel;
			if(answer > sum) answer = sum;
		} else {
			if(answer > cnt) answer = cnt;
		}						
	}

	private void moveChannel(StringBuilder c, int cnt) {
		int channel = Integer.parseInt(c.toString());
		
		if(cnt == len-1) {
			findChannel(channel, cnt);
		}
		if(cnt == len) {
			findChannel(channel, cnt);
			for(int i=0; i<=9; i++) {
				if(outoforder[i]) continue;
				StringBuilder chn = new StringBuilder(c);
				moveChannel(chn.append(i), cnt+1);
			}			
		} else if(cnt == len+1) {
			findChannel(channel, cnt);
		} else {
			for(int i=0; i<=9; i++) {
				if(outoforder[i]) continue;
				StringBuilder sb = new StringBuilder(c);
				moveChannel(sb.append(i), cnt+1);
			}
		}
	}
	
	/*
	 * 10
	 * 2
	 * 0 1 
	 * 
	 * 9
	 * 5
	 * 9 8 7 6 5
	 * 
	 */
	private void process() {
		// 번호를 눌러서 가보자
		for(int i=0; i<=9; i++) {
			if(outoforder[i]) continue;
			StringBuilder channel = new StringBuilder();
			moveChannel(channel.append(i), 1);
		}
		
		// 방향키가 더 빠르면 방향키로 가는 값으로 갱신
		int now = 100;
		if(N > now) {
			int sum = N - now;
			if(answer > sum) answer = sum;
		} else if(N<now) {
			int sum = now - N;
			if(answer > sum) answer = sum;				
		} else {
			answer = 0;
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

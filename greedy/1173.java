package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,m,M,T,R;
	private int X; // 현재 맥박
	private int time = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = m;
	}
	
	private void process() {
		while(N!=0) {
			if(X+T <= M) {
				X+=T;
				time++;
				N--;
			}
			// 운동 시간 다 채웠으면 끝
			if(N==0) break;
			// 운동 할 수 있으면 더 한다.
			if(X+T <= M) continue;
			// 맥박 회복 (운동할 수 있을 때까지)
			while(X+T > M) {
				X-=R;
				time++;
				// 최소 맥박이라면 더 이상 시간 쓸 필요없다.
				if(X<=m) {
					X=m;
					break;
				}
			}
			// 이 경우는 최소 맥박으로도 운동을 못 하는 경우 (-1)
			if(X+T > M) {
				time = -1;
				break;
			}
		}
		System.out.println(time);
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

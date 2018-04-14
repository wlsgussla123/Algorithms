package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Schedule {
	int time;
	int cost;
	public Schedule(int time, int cost) {
		this.time = time;
		this.cost = cost;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private int ans = 0;
		private ArrayList<Schedule> list = new ArrayList();
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				int time = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				list.add(new Schedule(time, cost));
			}
		}
		
		private void solution(int idx, int sum) throws IOException {
			if(idx > N) return;
			ans = ans > sum ? ans : sum;
			
			for(int i=idx; i<N; i++) {
				solution(i + list.get(i).time, sum + list.get(i).cost);
			}
		}
		
		public void run() throws IOException {
			input();
			for(int i=0; i<N; i++) {
				solution(i+list.get(i).time, list.get(i).cost);
			}
			bw.write(String.valueOf(ans));
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

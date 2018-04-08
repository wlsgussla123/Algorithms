package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private long[] arr;
		private	long A,B;
		private long ans;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			arr = new long[N];
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}
			st = getStringTokenizer();
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
		}
		
		private void solution(int idx) {
			long num = arr[idx];
			num -= A; // 총 감독관
			ans++;
			if(num <= 0) return;
			ans += num/B; // 부감독관
			num %= B;
			if(num <= 0) return;
			ans++;
		}
		
		public void run() throws IOException {
			input();
			for(int i=0; i<N; i++) {
				solution(i);
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

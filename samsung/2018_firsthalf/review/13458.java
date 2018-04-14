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
		private long B,C;
		private long ans = 0;
		
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
			B = Long.parseLong(st.nextToken());
			C = Long.parseLong(st.nextToken());
		}
		
		private void solve() {
			int idx = 0;
			while(idx<N) {
				long cnt = 1;
				long num = arr[idx] - B;
				if(num <= 0) {
					idx++;
					ans += cnt;
					continue;
				}
				
				cnt += num/C;
				num = num%C;
				if(num <= 0) {
					idx++;
					ans += cnt;
					continue;
				}
				
				cnt++;
				ans+= cnt;
				idx++;
				continue;
			}
		}
		
		public void run() throws IOException {
			input();
			solve();
			bw.write(String.valueOf(ans)+"\n");
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

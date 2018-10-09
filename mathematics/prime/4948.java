package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}
}

class Solution {
	private int n = 0;
	private InputClass inputClass = new InputClass();
	private ArrayList<Boolean> isPrime;
	
	public void run() throws IOException {
		do {
			inputClass.input();
			solve();
		} while(n != 0);
		
		inputClass.close();
	}
	
	private void solve() throws IOException {
		if(n==0) return;
		
		isPrime = new ArrayList<Boolean>(n*2+1);
		isPrime.add(false);
		isPrime.add(false);
		
		for(int i=2; i<=n*2; i++) {
			isPrime.add(i, true);
		}
		
		for(int i=2; i<=n*2; i++) {
			for(int j=i+i; j<=n*2; j=j+i) {
				if(j%i == 0) {
					isPrime.set(j, false);
				}
			}
		}
		
		int ans = 0;
		for(int i=n+1; i<=n*2; i++) {
			if(isPrime.get(i)) {
				ans++;
			}
		}
		
		inputClass.bw.write(ans+"\n");
	}
	
	class InputClass {
		private BufferedWriter bw;
		private BufferedReader br;
		private StringTokenizer st;
		
		public InputClass() {
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			br = new BufferedReader(new InputStreamReader(System.in));
			st = null;
		}
		
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		public void input() throws IOException {
			st = getStringTokenizer();
			n = Integer.parseInt(st.nextToken());
		}
		
		public void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}




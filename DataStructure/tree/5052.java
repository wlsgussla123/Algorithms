package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Trie {
	private Trie[] next;
	private boolean isExist;
	private final int SIZE = 10;
	
	public Trie() {
		next = new Trie[SIZE];
		isExist = false;
	}
	
	public void insert(String key, int idx) {
		if(key.length() == idx) {
			isExist = true;
		} else {
			int curr = key.charAt(idx) - '0';
			if(next[curr] == null)
				next[curr] = new Trie();
			next[curr].insert(key, idx+1);
		}
	}
	
	public boolean checkExist(String key, int idx) {
		if(key.length() == idx) return false;
		if(isExist) {
			return true;
		}
		
		int curr = key.charAt(idx) - '0';
		return next[curr].checkExist(key, idx+1);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private String[] strs;
		private Trie root;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			strs = new String[N];
			root = new Trie();
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				strs[i] = st.nextToken();
				root.insert(strs[i], 0);
			}
		}
		
		private void solve() {
			for(int i=0; i<N; i++) {
				if(root.checkExist(strs[i], 0)) {
					System.out.println("NO");
					return;
				}
			}
			System.out.println("YES");
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solve();
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

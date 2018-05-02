package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Trie {
	private static int N = 0;
	private Trie[] next;
	private boolean finish = false;
	private final int SIZE = 128;
	private int cnt = 0;
	private String str = null;
	
	public Trie() {
		next = new Trie[SIZE];
		cnt = 0;
		str = null;
	}
	
	public void insert(String key, int idx) {
		if(key.length() == idx) {
			finish = true;
			cnt++;
			N++;
			str = key;
//			System.out.println(key + " " + cnt);
			return;
		}
		
		int curr = key.charAt(idx) - ' ';
		if(next[curr] == null) {
			next[curr] = new Trie();
		}
		next[curr].insert(key, idx+1);
	}
	
	public boolean find(String key, int idx) {
		if(key.length() == idx) {
			return finish == true ? true : false;
		}
		
		int curr = key.charAt(idx) - ' ';
		if(next[curr] == null) {
			return false;
		}
		
		return next[curr].find(key, idx+1);
	}
	
	public void print() {
		if(cnt > 0) {
			System.out.printf("%s %.4f\n", str, 100.0 * cnt/N);
		}
		
		for(int i=0; i<128; i++) {
			if(next[i] != null) {
				next[i].print();
			}
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private Trie root = new Trie();
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), "");
		}
		
		private void input() throws IOException {
			String str = null;
			while(true) {
				str = br.readLine();
				if(str == null) break;
				root.insert(str, 0);
			}
		}
		
		public void run() throws IOException {
			input();
			root.print();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

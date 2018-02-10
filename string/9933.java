package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private String[] words;
	private String answer;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		words = new String[N];
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			words[i] = st.nextToken();
		}
	}
	
	private boolean check(String s1, String s2) {
		char[] c1;
		char[] c2;
		if(s1.length() > s2.length()) {
			c1 = s1.toCharArray();
			c2 = s2.toCharArray();
		} else {
			c1 = s2.toCharArray();
			c2 = s1.toCharArray();
		}
		
		int idx = 0;
		int len = c1.length;
		for(int i=len-1; i>=len/2; i--) {
			if(c1[i] != c2[idx++]) {
				return false;
			}
		}
		answer = String.valueOf(c1);
		
		return true;
	}
	
	private void process() {
		boolean flag = false;
		for(int i=0; i<N; i++) {
			if(flag) break;
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				if(check(words[i], words[j])) {
					flag = true;
					break;
				}
			}
		}
		
		if(answer == null) {
			answer = words[0].toString();
			System.out.println(answer.length() + " " + answer.charAt(answer.length()/2));
		} else {
			System.out.println(answer.length() + " " + answer.charAt(answer.length()/2));
		}
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

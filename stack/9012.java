package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Task {
	private Stack<Character> s1 = new Stack<>();
	private Stack<Character> s2 = new Stack<>();
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		char[] input = st.nextToken().toCharArray();
		for(int i=0; i<input.length; i++) {
			s1.push(input[i]);
		}
	}
	
	private void solution() {
		while(!s1.isEmpty()) {
			char c = s1.pop();
			if(c  == '(') {
				if(s2.isEmpty()) {
					System.out.println("NO");
					return;
				}
				s2.pop();
			} else {
				s2.push(c);
			}
		}
		
		if(s2.size() != 0) System.out.println("NO");
		else System.out.println("YES");
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		for(int i=1; i<=T; i++) {
			input();
			solution();
			s1.clear();
			s2.clear();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

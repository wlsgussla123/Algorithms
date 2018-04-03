package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Operation {
	char op;
	char data;
	public Operation(char op, char data) {
		this.op = op;
		this.data = data;
	}
}

class Task {
	private String str;
	private int N;
	private ArrayList<Operation> ops = new ArrayList<>();
	private Stack<Character> s1 = new Stack<>();
	private Stack<Character> s2 = new Stack<>();
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		str = st.nextToken();
		int len = str.length();
		for(int i=0; i<len; i++) {
			s1.push(str.charAt(i));
		}
		
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			char c1 = st.nextToken().charAt(0);
			if(c1 != 'P') {
				ops.add(new Operation(c1, ' '));
			} else {
				char c2 = st.nextToken().charAt(0);
				ops.add(new Operation(c1, c2));
			}
		}
	}
	
	private void solution() throws IOException {
		for(Operation op : ops) {
			if(op.op == 'L') {
				if(!s1.isEmpty()) 
					s2.push(s1.pop());
			} else if(op.op == 'D') {
				if(!s2.isEmpty())
					s1.push(s2.pop());
			} else if(op.op == 'B') {
				if(!s1.isEmpty()) s1.pop();
			} else {
				s1.push(op.data);
			}
		}
		
		while(!s2.isEmpty()) {
			s1.push(s2.pop());
		}
		
		StringBuilder sb = new StringBuilder();
		while(!s1.isEmpty()) {
			sb.append(s1.pop());
		}
		System.out.println(sb.reverse());
	}

	public void run() throws IOException {
		input();
		solution();
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	new Task().run();
    }
}

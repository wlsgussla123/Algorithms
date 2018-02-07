package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
	int d;
	char c;
	public Node(int depth, char c) {
		this.d = depth;
		this.c = c;
	}
}

class Task {
	private Stack<Node> s1 = new Stack<Node>();
	private Stack<Node> s2 = new Stack<Node>();
	private Stack<Integer>[] sumList = new Stack[101];
	private int answer = 0;
	private int preDepth = 0;
	private boolean isMatching = true;

	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	// 입력의 depth는 매우 잘 나오는 편
	private void input() throws IOException {
		st = getStringTokenizer();
		char[] input = st.nextToken().toCharArray();
		int depth = 1;
		for(int i=0; i<input.length; i++) {
			// 입력에 따라 depth 조절 (시작 depth는 1이다)
			if(input[i] == '(' || input[i] == '[') {
				Node node = new Node(depth++, input[i]);
				s1.push(node);
			} else if(input[i] == ')' || input[i] == ']'){
				Node node = new Node(--depth, input[i]);
				s1.push(node);
			}
		}
		
		for(int i=1; i<=100; i++) sumList[i] = new Stack<Integer>();
	}
	
	private void calculator(char c) {
		int num = (c == '[' ? 3 : 2);
		int d1 = s1.peek().d;
		int d2 = s2.peek().d;
		char c1 = s1.peek().c;
		char c2 = s2.peek().c;
		s1.pop();
		s2.pop();
		if((c == '[' && c2 != ']') || (c == '(' && c2 != ')') ) {
			answer = 0;
			isMatching = false;
			return;
		}
		
		if(preDepth == 0) {
			answer = num;
			preDepth = d1;
		} else {
			if(preDepth > d1) {
				answer *= num;
				preDepth = d1;
			} else if(preDepth < d1) {
				sumList[preDepth].push(answer);
				answer = num;
				preDepth = d1;
			} else {
				answer += num;
			}
		}
	}

	private void process() throws IOException {
		while(!s1.isEmpty()) {
			// 일단 닫음의 괄호는 s2에 넣어두고
			if(s1.peek().c == ']' || s1.peek().c == ')') {
				s2.push(s1.pop());
			}
			// 열림의 쌍이 나오면 닫음의 괄호와 매칭
			else {
				if(s2.isEmpty()) {
					answer = 0;
					break;
				}
				calculator(s1.peek().c);
				if(!isMatching) break;
				if(!sumList[preDepth].empty()) {
					answer += sumList[preDepth].pop();
				}
				
				if(s2.isEmpty() && !s1.isEmpty()) {
					sumList[preDepth].push(answer);
					answer = 0;
					preDepth = 0;
				}
			} 
		}
		if(!s2.isEmpty()) answer = 0;
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		process();
	}
	
	private void print() {
		while(!s1.isEmpty()) {
			if(s1.peek().d != 0) {
				System.out.println(s1.peek().c + " " + s1.peek().d);
			} else {
				System.out.println(s1.peek().c - '0' + " " + s1.peek().d);
			}
			s1.pop();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

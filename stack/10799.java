package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Pipe {
	int left;
	int right;
	public Pipe(int l, int r) {
		this.left = l;
		this.right = r; 
	}
}

class Node {
	int idx;
	char c;
	public Node(int idx, char c) {
		this.idx = idx;
		this.c = c;
	}
}

class Task {
	private String str;
	private ArrayList<Integer> laser = new ArrayList<>();
	private ArrayList<Pipe> pipe = new ArrayList<>();
	private Stack<Node> s1 = new Stack<>();
	private Stack<Node> s2 = new Stack<>();
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		str = st.nextToken();
		int len = str.length();
		for(int i=1; i<=len; i++) {
			s1.push(new Node(i, str.charAt(i-1)));
		}
	}
	
	// 레이저와 파이프 좌표 값 구하기
	private void analsys() {
		char prev = ' ';
		while(!s1.isEmpty()) {
			Node cur = s1.pop();
			
			// 여는 괄호
			if(cur.c == '(') {
				// 이전에 닫는 괄호였다면, 레이저 
				if(prev == ')') {
					if(!s2.isEmpty()) {
						s2.pop();
						laser.add(cur.idx);
					}
				} else {
					// 이전에 닫는 괄호가 아닌데 여는 괄호가 또 나오면, 파이프
					if(!s2.isEmpty()) {
						Node next = s2.pop();
						pipe.add(new Pipe(cur.idx, next.idx));
					}
				}
			} else {
				// 닫는 괄호는 s2에 넣고보자.
				s2.push(cur);
			}
			prev = cur.c;
		}
	}
	
	// 레이저와 파이프 좌표 값을 이용하여 자르기
	private void solution() {
		int cnt = 0;
		for(Pipe p : pipe) {
			cnt++;
			for(Integer l : laser) {
				if(p.left < l && l < p.right) cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	public void run() throws IOException {
		input();
		analsys();
		solution();
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

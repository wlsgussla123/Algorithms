package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
	int idx;
	int value;
	public Node(int i, int v) {
		this.idx = i;
		this.value = v;
	}
}

class Task {
	private int N;
	private Stack<Node> s1 = new Stack<>();
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		for(int i=1; i<=N; i++) {
			int value = Integer.parseInt(st.nextToken());
			while(!s1.isEmpty()) {
				Node node = s1.peek();
				if(node.value >= value) {
					bw.write(String.valueOf(node.idx + " "));
					break;
				} else {
					s1.pop();
				}
			}
			if(s1.isEmpty()) bw.write(String.valueOf("0 "));
			s1.push(new Node(i, value));
		}
	}
	
	public void run() throws IOException {
		input();
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

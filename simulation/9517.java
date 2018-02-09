package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {
	int time;
	char answer;

	public Node(int t, char a) {
		this.time = t;
		this.answer = a;
	}
}

class Task {
	private int K, N;
	private Node[] list;

	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		K = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		list = new Node[N + 1];

		for (int i = 1; i <= N; i++) {
			st = getStringTokenizer();
			int t = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			list[i] = new Node(t, c);
		}
	}

	private void process() throws IOException {
		int time = 1;
		int index = K;
		int number = 1;
		while (time <= 210) {
			if(number > N) break;
			// 경과 시간 내에 폭탄이 터진다면 종료 아니면 시간을 그만큼 더한다.
			if(time + list[number].time <= 210) time += list[number].time;
			else break;
			
			if(list[number].answer == 'T') {
				index = index%8 + 1;
				number++;
			} else {
				number++;
			}
		}
		System.out.println(index);
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

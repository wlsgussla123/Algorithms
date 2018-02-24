import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class CircularQueue {
	int size;
	ArrayList<Integer> data;
	// 데이터는 1~N까지 있다.
	public CircularQueue(int N) {
		data = new ArrayList<Integer>();
		size = N;
		for(int i=1; i<=N; i++) {
			data.add(i);
		}
	}
	
	public int poll() {
		int d = data.get(0);
		data.remove(0);
		return d;
	}
	
	public void moveLeft() {
		int d = data.get(0);
		data.remove(0);
		data.add(d);
	}
	
	public void moveRight() {
		int d = data.get(data.size()-1);
		data.remove(data.size()-1);
		data.add(0,d);
	}
	
	public void print() {
		for(int i=0; i<data.size(); i++) {
			System.out.print(data.get(i) + " ");
		}
		System.out.println();
	}
	
	public int leftIdx(int num) {
		for(int i=0; i<data.size(); i++) {
			if(num == data.get(i)) return i;
		}
		return 10000;
	}
	public int rightIdx(int num) {
		for(int i=0; i<data.size(); i++) {
			if(num == data.get(i)) return data.size()-i;
		}
		return 10000;
	}
}

class Task {
	private int N,M;
	private ArrayList<Integer> list = new ArrayList<Integer>();
	private CircularQueue cq;
	private int answer = 0;
	// input
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		for(int i=0; i<M; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		cq = new CircularQueue(N);
	}
	
	private void solution() {
		while(list.size() > 0) {
			int left = cq.leftIdx(list.get(0));
			int right = cq.rightIdx(list.get(0));
			
			if(left <= right) {
				while(cq.data.get(0) != list.get(0)) {
					cq.moveLeft();
					answer++;
				}
				cq.data.remove(0);
				list.remove(0);
			} else {
				while(cq.data.get(0) != list.get(0)) {
					cq.moveRight();
					answer++;
				}
				cq.data.remove(0);
				list.remove(0);
			}
		}
		System.out.println(answer);
	}

	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

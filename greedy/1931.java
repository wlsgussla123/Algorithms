package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

class Schedule {
	private int s;
	private int e;
	public Schedule(int s, int e) {
		this.s = s;
		this.e = e;
	}
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	public int getE() {
		return e;
	}
	public void setE(int e) {
		this.e = e;
	}
}

class Task {
	private int N;
	private long answer = 1;
	private ArrayList<Schedule> list = new ArrayList<>();
	private final Scanner sc = new Scanner(System.in);
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list.add(new Schedule(s, e));
		}
	}
	
	private void solution() throws IOException {
		list.sort(new Comparator<Schedule>() {
			@Override
			public int compare(Schedule o1, Schedule o2) {
				if(o1.getE() != o2.getE()) {
					return o1.getE() - o2.getE();
				} else {
					return o1.getS() - o2.getS();
				}
			}
		});
		
		int time = list.get(0).getE();
		for(int i=1; i<list.size(); i++) {
			if(time > list.get(i).getS()) continue;
			time = list.get(i).getE();
			answer++;
		}
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

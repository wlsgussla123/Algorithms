package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Candidate {
	int id;
	int time;
	int cnt;
	public Candidate(int id, int time) {
		this.id = id;
		this.time = time;
		this.cnt = 1;
	}
};

class Task {
	private int N,M;
	private List<Candidate> list = new ArrayList<Candidate>();
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		int num = 0;
		boolean find = false;
		for(int i=1; i<=M; i++) {
			int id = Integer.parseInt(st.nextToken());
			for(int j=0; j<list.size(); j++) {
				if(id == list.get(j).id) {
					list.get(j).cnt++;
					find = true;
					break;
				}
			}
			
			if(find) {
				find = false;
				continue;
			} else {
				if(list.size() == N) {
					int minIdx = 0;
					for(int j=1; j<list.size(); j++) {
						if(list.get(minIdx).cnt != list.get(j).cnt) {
							// minIdx보다 추천수가 더 작다면
							if(list.get(minIdx).cnt > list.get(j).cnt) {
								minIdx = j;
							}
						} else {
							// 더 오래됐다면
							if(list.get(minIdx).time > list.get(j).time) {
								minIdx = j;
							}
						}
					}
					list.remove(minIdx);
				}
				
				list.add(new Candidate(id, i));
			}
		}
	}
	
	public void run() throws IOException {
		input();
		print();
	}
	
	private void print() {
		Collections.sort(list, new Comparator<Candidate>() {
			@Override
			public int compare(Candidate o1, Candidate o2) {
				// TODO Auto-generated method stub
				return o1.id - o2.id;
			}
		});
		for(Candidate c : list) {
			System.out.print(c.id + " ");
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

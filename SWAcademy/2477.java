package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Person {
	int time;
	int cusId;
	int aId;
	int bId;
	
	public Person(int time, int cusId, int aId, int bId) {
		this.time = time;
		this.cusId = cusId;
		this.aId = aId;
		this.bId = bId;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M,K,A,B;
		private int[] a;
		private int[] b;
		private int[] time_a;
		private int[] time_b;
		private PriorityQueue<Person> p_q;
		private PriorityQueue<Person> a_q; // reception 후에 모인 우선순위 큐
		private ArrayList<Integer> ans;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			a = new int[N+1];
			b = new int[M+1];
			time_a = new int[N+1];
			time_b = new int[M+1];
			
			p_q = new PriorityQueue(new Comparator<Person>() {
				@Override
				public int compare(Person p1, Person p2) {
					if(p1.time != p2.time) {
						return p1.time - p2.time;
					} else {
						return p1.cusId - p2.cusId;
					}
				}
			});
			
			a_q = new PriorityQueue(new Comparator<Person>() {
				@Override
				public int compare(Person p1, Person p2) {
					if(p1.time != p2.time) {
						return p1.time - p2.time;
					} else {
						return p1.aId - p2.aId;
					}
				}
			});
			
			ans = new ArrayList();
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			init();
			
			st = getStringTokenizer();
			for(int i=1; i<=N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			
			st = getStringTokenizer();
			for(int i=1; i<=M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			
			st = getStringTokenizer();
			for(int i=1; i<=K; i++) {
				int time = Integer.parseInt(st.nextToken());
				p_q.add(new Person(time, i, 0, 0));
			}
		}
		
		private void solve() throws IOException {
			// 접수처
			int time = p_q.peek().time;
			boolean isNext = true;
			while(!p_q.isEmpty()) {
				Person p = p_q.peek();
				isNext = true;
				
				if(p.time <= time) {
					for(int i=1; i<=N; i++) {
						if(time_a[i] <= time) {
							time_a[i] = time + a[i];
							p_q.poll();
							a_q.add(new Person(time_a[i], p.cusId, i, 0));
							isNext = false; // 다음에도 같은 시간에 들어올 수도 있으니까
							break;
						}
					}
				}
				
				if(isNext) {
					time++;
				}
			}
			
			time = a_q.peek().time;
			while(!a_q.isEmpty()) {
				Person p = a_q.peek();
				isNext = true;
				if(p.time <= time) {
					for(int i=1; i<=M; i++) {
						if(time_b[i] <= time) {
							time_b[i] = time + b[i];
							a_q.poll();
							isNext = false;
							if(p.aId == A && i == B) {
								ans.add(p.cusId);
							}
							break;
						}
					}
				}
				
				if(isNext) {
					time++;
				}
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solve();
				int sum = 0;
				for(Integer num : ans) {
					sum += num;
				}
				
				if(sum == 0) bw.write("#" + i + " -1\n");
				else bw.write("#" + i + " " + String.valueOf(sum)+"\n");
			}
			close();
		}
		
		private void print() {
//			while(!p_q.isEmpty()) {
//				Person p = p_q.poll();
//				System.out.println(p.cusId + " " + p.time);
//			}
			
			System.out.println("cusId aId time");
			while(!a_q.isEmpty()) {
				Person p = a_q.poll();
				System.out.println(p.cusId + " " + p.aId + " " + p.time);
			}
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

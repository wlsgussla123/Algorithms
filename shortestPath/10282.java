package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Task {
	private List<List<int[]>> graph;
	private int n,d,c, T;
	private int a,b,s;
	private int[] timeList;
	private int answerIndex;
	private int answerCnt = 0;
	private final int INF = 987654321;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	// 테스트케이스를 위한 초기화
	private void init() {
		graph = new LinkedList<>();
		for(int i=0; i<=n; i++) {
			graph.add(new LinkedList<>());
		}
		
		timeList = new int[n+1];
		for(int i=1; i<=n; i++) {
			timeList[i] = INF;
		}
		
		answerIndex = 0;
		answerCnt = 0;
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=d; i++) {
			st = getStringTokenizer();
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			int[] edge = {a,s}; // 나를 의존하고 있는 컴퓨터, 감염 걸리는 시간
			graph.get(b).add(edge);
		}
	}
	
	// 바이러스가 퍼진다.
	private void virus(int start) {
		PriorityQueue<int[]> headq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[1] - o2[1];
			}
		});
		
		timeList[start] = 0;
		
		int[] cur = {start, 0};
		headq.add(cur);
		
		int curCost, curIndex=0, nextCost, nextIndex;
		while(!headq.isEmpty()) {
			cur = headq.poll();
			curIndex = cur[0];
			curCost = cur[1];
			for(int[] edge: graph.get(curIndex)) {
				nextIndex = edge[0];
				nextCost = edge[1];
				
				if(timeList[nextIndex] > curCost + nextCost) {
					timeList[nextIndex] = curCost + nextCost;
					int[] nextEdge = {nextIndex, curCost + nextCost};
					headq.add(nextEdge);
				}
			}
		}
	}
	
	private void getAnswer() {
		int max = 0;
		for(int i=1; i<=n; i++) {
			if(timeList[i] != INF) {
				answerCnt++;
				
				if(timeList[i] > max) {
					max = timeList[i];
				}
			}
		}
		
		System.out.print(answerCnt + " ");
		System.out.println(max);
	}

	public void run() throws IOException {
		st = getStringTokenizer();
		T = Integer.parseInt(st.nextToken());
		
		while(T>0) {
			input();
			virus(c);
			getAnswer();
			
			T--;
		}
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private final int N=4,M=8;
	private int K;
	private int[][] gearWheels = new int[N][M];
	private Queue<int[]> q = new LinkedList<int[]>(); // 회전 순서를 담은 큐
	private boolean[] visited = new boolean[N];
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=0; j<M; j++) {
				gearWheels[i][j] = input[j]-'0';
			}
		}
		st = getStringTokenizer();
		K = Integer.parseInt(st.nextToken());
		for(int i=0; i<K; i++) {
			st = getStringTokenizer();
			int index = Integer.parseInt(st.nextToken());
			int op = Integer.parseInt(st.nextToken());
			getOperation(index-1, op); // 회전을 시킬 톱니들을 큐에 넣는다.
			rotate(); // 실제 회전
			visited = new boolean[N];
		}
	}
	
	// 기준이 되는 톱니 idx1, 이게 돌아가면 돌아가야 하는 톱니바퀴 idx2 
	private boolean nextRotate(int idx1, int idx2) {
		if(gearWheels[idx1][2] == gearWheels[idx2][6]) return false;
		return true;
	}
	
	// 회전하는 톱니바퀴, 방향
	private void getOperation(int idx, int o) {
		visited[idx] = true;
		int[] initOp = {idx, o};
		q.add(initOp);
		
		// 다음에 회전시킬 것들이 있는지 저장하자...
		if(idx == 0) {
			if(!visited[idx+1] && nextRotate(idx, idx+1)) {
				visited[idx+1] = true;
				getOperation(idx+1, o*-1);
			}
		} else if(idx == 1 || idx == 2) {
			if(!visited[idx+1] && nextRotate(idx, idx+1)) {
				visited[idx+1] = true;
				getOperation(idx+1, o*-1);
			}
			
			if(!visited[idx-1] && nextRotate(idx-1, idx)) {
				visited[idx-1] = true;
				getOperation(idx-1, o*-1);
			}
		} else {
			if(!visited[idx-1] && nextRotate(idx-1, idx)) {
				visited[idx-1] = true;
				getOperation(idx-1, o*-1);
			}
		}
	}
	
	private void rotate() {
		// 실제로 회전시키는 코드 ...
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int index = cur[0];
			int op = cur[1];
			
			// 시계
			if(op == 1) {
				int temp = gearWheels[index][M-1];
				for(int i=M-1; i>0; i--) {
					gearWheels[index][i] = gearWheels[index][i-1];
				}
				gearWheels[index][0] = temp;
			// 반시계
			} else {
				int temp = gearWheels[index][0];
				for(int i=0; i<M-1; i++) {
					gearWheels[index][i] = gearWheels[index][i+1];
				}
				gearWheels[index][M-1] = temp;
			}
		}
	}
	
	private void getScore() {
		int score = 0;
		for(int i=0; i<N; i++) {
			if(gearWheels[i][0] == 0) continue;
			if(i==0 && gearWheels[i][0] == 1) score++;
			if(i==1 && gearWheels[i][0] == 1) score+=2;
			if(i==2 && gearWheels[i][0] == 1) score+=4;
			if(i==3 && gearWheels[i][0] == 1) score+=8;
		}
		System.out.println(score);
	}
	
	public void run() throws IOException {
		input();
		getScore();
	}
	
	private void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(gearWheels[i][j]);
			}
			System.out.println();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

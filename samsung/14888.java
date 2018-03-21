package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] number;
	private int[] ops = new int[4];
	private long min = 2000000000;
	private long max = -2000000000;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		number = new int[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		st = getStringTokenizer();
		for(int i=0; i<4; i++) {
			ops[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void calc(int idx, int op, int[] ops, long value) {		
		if(op == 0) {
			value += number[idx];
		} else if(op == 1) {
			value -= number[idx];
		} else if(op == 2) {
			value *= number[idx];
		} else {
			value /= number[idx];
		}
		
		if(idx==N-1) {
			max = max > value ? max : value;
			min = min < value ? min : value;
			return;
		}
		
		int[] temp = new int[4];
		for(int i=0; i<4; i++) {
			temp[i] = ops[i];
		}
		
		for(int i=0; i<4; i++) {
			if(temp[i] == 0) continue;
			temp[i]--;
			calc(idx+1, i, temp, value);
			temp[i]++;
		}
	}
	
	private void solution() {
		for(int i=0; i<4; i++) {
			if(ops[i] == 0) continue;
			ops[i]--;
			calc(1, i, ops, number[0]);
			ops[i]++;
		}
	}
	
	public void run() throws IOException {
		input();
		solution();
		System.out.println(max);
		System.out.println(min);
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

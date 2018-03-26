package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private ArrayList<Integer> list = new ArrayList<>();
	
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
		M = Integer.parseInt(st.nextToken());
	}
	
	private void solution() {
		for(int i=1; i<=M; i++) {
			int num = i*i;
			if(num >= N & num <= M) list.add(num);
		}
		
		if(list.size() == 0) {
			System.out.println("-1");
			return;
		}
		
		int sum = 0;
		int min = 10000;
		for(Integer n : list) {
			sum += n;
			min = min > n ? n : min;
		}
		System.out.println(sum);
		System.out.println(min);
	}
	
	public void run() throws IOException {
		input();
		solution();
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

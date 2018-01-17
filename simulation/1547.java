package algo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] arr = new int[4];
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=3; i++) arr[i] = i;
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			swap(x, y);
		}
		
		for(int i=1; i<=3; i++) {
			if(arr[i] == 1) {
				System.out.println(i);
				break;
			}
		}
	}
	
	private void swap(int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	public void run() throws IOException {
		input();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] alphabet = new int[26];
	private String[] strs;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		strs = new String[N];
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			strs[i] = st.nextToken();
			int len = strs[i].length();
			int pow = (int)Math.pow(10, strs[i].length()-1);
			for(int j=0; j<len; j++) {
				alphabet[strs[i].charAt(j) - 'A'] += pow;
				pow/=10;
			}
		}
	}

	void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void process() {
		qsort(alphabet, 0, alphabet.length-1);
		int sum = 0;
		for(int i=0; i<10; i++) sum += alphabet[i] * (10-i-1);
		System.out.println(sum);
	}
	
	public void run() throws IOException {
		input();
		process();
		close();
	}
	
	private void qsort(int arr[], int front, int rear) {
		if(front>=rear) return;
		int i = front-1, j = rear, pivot = arr[rear];
		while(true) {
			while(arr[++i]>pivot && i<rear);
			while(arr[--j]<pivot && j>front);
			if(i>=j) break;
			swap(arr, i, j);
		}
		swap(arr, i, rear);
		qsort(arr, front, i-1);
		qsort(arr, i+1, rear);
	}
	
	private void swap(int arr[], int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

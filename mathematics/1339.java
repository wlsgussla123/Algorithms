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
	private int[] score = new int[26];
	private char[][] str = new char[10][9];
	private int cnt = 0;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		for(int i=0; i<26; i++) score[i] = -1;
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			str[i] = st.nextToken().toCharArray();
			int pow = (int)Math.pow(10, str[i].length-1);
			for(int j=0; j<str[i].length; j++) {
				alphabet[str[i][j] - 'A'] += pow;
				pow = pow/10;
			}
		}
	}

	void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void count() {
		for(int i=0; i<26; i++)
			if(alphabet[i] != 0) cnt++;
	}
	
	// score[index]에 가중치가 들어있다.
	private void findScore() {
		int w = 9;
		while(cnt>0) {
			int maxIndex = 0;
			for(int i=1; i<26; i++) {
				if(alphabet[maxIndex] < alphabet[i]) {
					maxIndex = i;
				}
			}
			score[maxIndex] = w--;
			alphabet[maxIndex] = 0;
			cnt--;
		}
	}
	
	private void calculate() {
		int index = 0;
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<str[i].length; j++) {
				index = str[i][j] - 'A';
				str[i][j] = (char)(score[index] + '0');
			}
			sum += Integer.parseInt(String.valueOf(str[i]));
		}
		System.out.println(sum);
	}

	public void run() throws IOException {
		input();
		count();
		findScore();
		calculate();
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

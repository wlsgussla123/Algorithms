package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private ArrayList<Integer> answer = new ArrayList<Integer>();
	private Scanner sc = new Scanner(System.in);
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	// 입력
	private void input() throws IOException {
		int idx = 1;
		while(sc.hasNext()) {
			String str = sc.next();
			if(str.indexOf("FBI") != -1) {
				answer.add(idx);
			}
			idx++;
		}
		
		if(answer.size() == 0) System.out.println("HE GOT AWAY!");
		else {
			for(int num : answer) {
				System.out.print(num + " ");
			}
		}
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

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private Scanner sc = new Scanner(System.in);
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		while(sc.hasNextLine()) {
			String str = sc.nextLine();
			if(str.equals("END")) break;
			for(int i=str.length()-1; i>=0; i--) {
				System.out.print(str.charAt(i));
			}
			System.out.println();
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

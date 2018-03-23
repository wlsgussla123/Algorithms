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
	private ArrayList<String> sound;
	private Scanner sc = new Scanner(System.in);
	private StringTokenizer st = null;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		sound = new ArrayList<>();
	}
	
	private void input() throws IOException {
		init();
		int idx = 1;
		boolean flag = false;
		while(sc.hasNext()) {
			if(idx == 1) {
				String str = sc.nextLine();
 				StringBuilder sb = new StringBuilder();
				for(int i=0; i<str.length(); i++) {
					if(!(str.charAt(i) >= 'a' && str.charAt(i) <= 'z')) {
						sound.add(sb.toString());
						sb = new StringBuilder();
						continue;
					}
					sb.append(str.charAt(i));
				}
				sound.add(sb.toString());
				idx++;
			} else {
				String str = sc.next();
				if(str.equals("say?")) {
					break;
				}
				if(str.equals("goes")) {
					flag = true;
					continue;
				}
				
				if(flag) {
					for(int i=0; i<sound.size(); i++) {
						if(sound.get(i).equals(str)) {
							sound.remove(i);
							i = i-1;
							continue;
						}
					}
					flag = false;
				}
			}
		}
	}
	
	private void solution() {
		for(String str : sound) {
			System.out.print(str + " ");
		}
		System.out.println();
	}
	
	public void run() throws IOException {
		int T = sc.nextInt();
		for(int i=1; i<=T; i++) {
			sc.nextLine();
			input();
			solution();
		}
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

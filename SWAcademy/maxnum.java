package algo;

import java.io.IOException;
import java.util.Scanner;

class Task {
	private int[] score;
	private int max;
	private Scanner sc = new Scanner(System.in);
	
	public void run() throws IOException {
		for(int T=0; T<10; T++) {
			int N = sc.nextInt();
			score = new int[101];
			for(int i=0; i<1000; i++) {
				int num = sc.nextInt();
				score[num]++;
			}
			
			max = 0;
			int len = score.length;
			for(int i=1; i<len; i++) {
				if(score[max] <= score[i]) {
					max = i;
				}
			}
			
			System.out.println("#"+N+" "+max);
		}
	}
}
 
public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

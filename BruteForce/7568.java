package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Person {
	public int weight;
	public int height;
	public int rank;
	
	public Person(int weight, int height) {
		this.weight = weight;
		this.height = height;
		this.rank = 1;
	}
}

class Task {
	private int N;
	private Person[] people;
	private int[] answer;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private final Scanner sc = new Scanner(System.in); 
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		people = new Person[N+1];
		answer = new int[N+1];
	}
	
	private void input() throws IOException {
		N = sc.nextInt();
		init();
		
		int w,h;
		for(int i=1; i<=N; i++) {
			w = sc.nextInt();
			h = sc.nextInt();
			people[i] = new Person(w,h);
		}
	}
	
	private void process() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				if(people[i].weight < people[j].weight && people[i].height < people[j].height) {
					people[i].rank++;
				}
			}
		}
	}
	
	private void answer() {
		for(int i=1; i<=N; i++) {
			System.out.print(people[i].rank + " ");
		}
	}

	public void run() throws IOException {
		input();
		process();
		answer();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

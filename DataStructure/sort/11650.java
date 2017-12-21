package algo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Point implements Comparable<Point> {
	private int x;
	private int y;

	public Point() {}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void print() {
		System.out.println(this.x + " " + this.y);
	}
	
	@Override
	public int compareTo(Point o) {
		return Comparator.comparing(Point::getX)
				.thenComparing(Point::getY)
				.compare(this, o);
	}
}

class Task {
	private int N;
	private final Scanner sc = new Scanner(System.in);
	private Point[] pointList;
	
	private void input() {
		N = sc.nextInt();
		pointList = new Point[N];
		
		int x;
		int y;
		for(int i=0; i<N; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			pointList[i] = new Point(x, y);
		}
	}
	
	private void sort() {
		Arrays.sort(pointList);
	}
	
	private void print() {
		for(int i=0; i<N; i++) {
			pointList[i].print();
		}
	}
	
	public void run() {
		input();
		sort();
		print();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}

/*
 *	작성자 : 박진현
 *	백준 : 1629번 문제, 곱셈
 */
import java.util.Scanner;

public class Main {
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		long A,B,C;
		
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		
		long result = multi(A,B,C);
		System.out.println(result);
	}
	
	public static long multi(long A, long B, long C) {
		long result = 1;
		
		while(B > 0) {
			if(B%2 == 1) {
				result *= A; // 홀수인 경우에 A만큼 더 곱해줘야 하z
			}
			
			A = A * A; // A는 A 제곱
		}
		
		return result;
	}
}

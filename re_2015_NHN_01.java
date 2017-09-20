
import java.util.*;

class Main {
	public static int minNumber(int a, int b) {
		return a > b ? a-b : b-a;
	}
	
  public static void main(String[] args) {
		int n;
		Scanner scan = new Scanner(System.in);		
		n = scan.nextInt();
		
		int[] numbers = new int[n];
		int min=0;
		int result1 = 0;
		int result2 = 0;
		
		for(int i=0; i<n; i++) {
			numbers[i] = scan.nextInt();
		}
		
		Arrays.sort(numbers);
		min = minNumber(numbers[0], numbers[1]); // 처음의 차가 들어왔지 지금.
		result1 = numbers[0];
		result2 = numbers[1];
		
		for(int i=1; i<n-1; i++) {
			int temp = minNumber(numbers[i], numbers[i-1]);
			int temp2 = minNumber(numbers[i], numbers[i+1]);

			int value = temp > temp2 ? temp2 : temp;
			
			if(min > value) {
				result1 = numbers[i];
				result2 = numbers[i+1];
			}
			
			min = value;
		}
		
		System.out.println(result1 +" "+result2);
  }
}

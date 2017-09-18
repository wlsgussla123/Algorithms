/*
하나의 자연수가 주어질 때, 이 숫자를 일련의 과정을 통해 펠린드롬 넘버로 만들려고 한다. 펠린드롬 넘버란 앞뒤 어느 쪽에서 읽어도 동일한 값을 가지는 숫자를 말한다.

 예를 들어서 12321은 펠린드롬 넘버이고, 123210은 펠린드롬 넘버가 아니다.



 주어진 입력 숫자를 펠린드롬 넘버로 만들기 위해 사용할 연산 F는 아래와 같이 정의한다.



 F(n) = n + R(n)    (단, R(n)은 n을 좌우로 뒤집은 숫자)



 입력으로 자연수가 하나 주어질 때, 해당 자연수에 연산 F를 한 번 이상 적용하여 4자리 이내의 펠린드롬 넘버를 만들 수 있는지 판단하는 프로그램을 작성하시오.

 단, 아래와 같은 조건 중 하나 이상을 만족하면 불가능하다고 판단한다.

3번 이내의 연산으로 펠린드롬 넘버를 만들 수 없는 경우
연산 과정에서 결과가 10,000이상이 되 버리는 경우
만들 수 있는 경우가 여러가지라면 가장 적은 연산을 적용한 값을 선택한다.


 예를 들어서 57의 경우 F(57) = 57 + 75 = 132이다. 이 연산을 한번 더 적용하면 F(F(57)) = F(132) = 132 + 231 = 363 이 된다. 즉, 연산 F를 57에 두 번 적용하면 펠린드롬 넘버를 만들 수 있다.

57 -> 363
78 -> -1
85 -> 484
196 -> -1

*/

import java.util.Scanner;

class Main {
	static int cnt = 0;
	
  public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String num;
		int cnt = 0;
		
		num = scan.nextLine();
		System.out.println(paindrome(num));	
  }
	
	public static int paindrome(String num) {
		cnt++;
		
		if(num.length() > 4 || cnt > 3) {
			return -1;
		}
		
		String reverseNum = (new StringBuffer(num)).reverse().toString();
		int op1 = Integer.parseInt(num);
		int op2 = Integer.parseInt(reverseNum);
		
		String value = Integer.toString(op1 + op2);
		int len = value.length();
		boolean isPalindrome = true;
		
		for(int i=0; i<len/2; i++) {
			if(value.charAt(i) != value.charAt(len-1-i)) {
				isPalindrome = false;
			}
		}
		
		int result = Integer.parseInt(value);

		if(!isPalindrome) {	
			result = paindrome(value);
		}
				
		return result;
	}
}

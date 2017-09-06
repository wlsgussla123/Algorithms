
#include <stdio.h>

//
//  문제: https://www.acmicpc.net/problem/11052
//  Created by 박진현 on 2017. 9. 1..
//

int dp[10001] = {0,};
int p[10001] = {0,};

int max(int a, int b) { return a > b ? a : b; }

int solution(int n) {
	int i,j;
	
	for(i=1; i<=n; i++) {
		dp[i]=0;
		
		for(j=1; j<=i; j++) {
			dp[i] = max(dp[i], dp[i-j] + p[j]);
		}
	}
	
	return dp[i-1];
}

int main() {
	int n;
	int i;
	
	scanf("%d", &n);
		
	if (n<1 || n>1000) {
		printf("값의 범위를 넘었습니다.\n");
		return 0;
	} 
	
	for(i=1; i<=n; i++) {
		scanf("%d", &p[i]);
	}
	
	printf("%d\n", solution(n));

  return 0;
}

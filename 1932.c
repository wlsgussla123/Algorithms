
/*
	문제 : https://www.acmicpc.net/status/?user_id=wlsgussla123&problem_id=1932&from_mine=1
	작성자 : 박진현
	날짜 : 9월 14일
*/


#include <stdio.h>

int arr[501][501] = {0,};
int dp[501][501] = {0,};

int max (int a, int b) { return a > b ? a : b; }

int main() {
	int n;
	int i,j;
	int result=0;
	
	scanf("%d", &n);
	for(i=1; i<=n; i++) {
		for(j=1; j<=i; j++) {
			scanf("%d", &arr[i][j]);
		}
	}
	
	dp[1][1] = arr[1][1];
	for(i=2; i<=n; i++) {
		for(j=1; j<=i; j++) {
			if(j==1) {
				dp[i][j] = dp[i-1][j] + arr[i][j];
			} else if(j==i) {
				dp[i][j] = dp[i-1][j-1] + arr[i][j];
			} else {
				dp[i][j] = max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j];
			}
		}
	}
	
	for(i=1; i<=n; i++) {
		result = max(result, dp[n][i]);
	}
	
	printf("%d\n", result);
	
  return 0;
}

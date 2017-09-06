#include <stdio.h>

//
//  문제: https://www.acmicpc.net/problem/1149
//  Created by 박진현 on 2017.9.6.
//

int dp[1001][1001] = {0,};
int rgb[1001][1001] = {0,};

int min(int a, int b) { return a < b ? a : b; }

int solution(int n) {
	int i, j;
	int result;
	
	for(i=1; i<=n; i++) {
		for(j=1; j<=3; j++) {
			if(j==1) {
				// 빨강이면
				dp[i][j] = min(dp[i-1][j+1], dp[i-1][j+2]) + rgb[i][j];
			} else if(j==2) {
				// 파랑이면
				dp[i][j] = min(dp[i-1][j-1], dp[i-1][j+1]) + rgb[i][j];		
			} else {
				// 초록이면
				dp[i][j] = min(dp[i-1][j-1], dp[i-1][j-2]) + rgb[i][j];
			}
		}
	}
	
	result = min(min(dp[i-1][1], dp[i-1][2]), dp[i-1][3]);
	
	return result;
}

int main() {
	int i, j, n;
	
	scanf("%d", &n);
	if(n>1000)
		return 0;
	
	for(i=1; i<=n; i++) {
		for(j=1; j<=3; j++) {
			scanf("%d", &rgb[i][j]);
		}
	}

	solution(n);
	printf("%d\n", solution(n));
	
  return 0;
}

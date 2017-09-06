//
//  문제: https://www.acmicpc.net/problem/9465
//  Created by 박진현 on 2017.9.6.
//
#include <stdio.h>
#include <stdlib.h>

int dp[2][100001] = {0,};
int s[2][100001] = {0,};

int max(int a, int b) { return a > b ? a : b; }

int solution(int n) {
	int i,j;
	
	dp[0][0] = 0;
	dp[1][0] = 0;
	dp[0][1] = s[0][1];
	dp[1][1] = s[1][1];
	
	// 스티커를 띄면 상하좌우로 못 가므로 대각선으로 이동.
	// 대각선으로 이동할 때 두 칸 건너서 대각일 수도 있으므로 max를 통해 뭐가 더 큰지 확인
	for(i=2; i<=n; i++) {
		dp[0][i] = max(dp[1][i-1], dp[1][i-2]) + s[0][i];
		dp[1][i] = max(dp[0][i-1], dp[0][i-2]) + s[1][i];
	}

	return max(dp[0][n], dp[1][n]);
}

int main() {
	int n,T, tmp;
	int i,j,k=0;
	int * result;
	
	scanf("%d", &T); //테스트 케이스 입력
	tmp = T; // 나중에 테스트 케이스 각 결과를 반환할 때 사용할 조건 변수
	
	result = (int *)malloc(sizeof(int) * T);
	while(T>0) {
		scanf("%d", &n); //열 입력
		
		// 스티커 재입력 
		for(i=0; i<2; i++) {
			for(j=1; j<=n; j++) {
				scanf("%d", &s[i][j]);
			}
		}			
	
		result[k++] = solution(n);		
		// dp 초기화
		for(i=0; i<2; i++) {
			for(j=1; j<=n; j++) {
				dp[i][j] = 0;
			}
		}
		
		T--;
	}
	
	for(i=0; i<tmp; i++) {

		printf("%d\n", result[i]);
	}
	
  return 0;
}

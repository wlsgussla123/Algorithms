
#include <stdio.h>

int max(int a, int b) { return a > b ? a : b; }

int dp[301][2] = {0,};
int score[301] = {0,};

int solution(int n) {
	int i;

	dp[1][1] = score[1];
	for(i=2; i<=n; i++){
		dp[i][1] = max(dp[i-2][2], dp[i-2][1])+score[i]; // i번째가 연속 1번째인 경우는 2번 째 칸 앞에서 오는 경우
		dp[i][2] = dp[i-1][1]+score[i]; // i번째가 연속 2번째인 경우는 1번째 칸 앞에서 오는 경우
	}

	return max(dp[n][1], dp[n][2]);
}

int main() {
	int n;
	int i;
	
	scanf("%d", &n);
	
	score[0] = 0;
	for(i=1; i<=n; i++) {
		scanf("%d", &score[i]);
		
		if(score[i]>10000 || score[i]<1) {
			printf("허용 범위 초과\n");
			return 0;
		} 
	}
	
	printf("%d\n", solution(n));
		
  return 0;
}

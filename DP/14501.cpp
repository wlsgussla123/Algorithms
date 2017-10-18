/*
	작성자 : 박진현
	문제 : 백준 14501, 퇴사문제 

	설명 : dp와 dfs의 조화. (dp는 넉넉하게 하는게 좋을 것.)
*/

#include <iostream>
#include <algorithm>

using namespace std;

int N;
int dp[21][21] = { 0, };
int answer = 0;

void dfs(int end, int total_count) {
	answer = max(answer, total_count);

	for (int i = end + 1; i <= N; i++) {
		for (int j = i; j <= N; j++) {
			if (dp[i][j]) {
				dfs(j, dp[i][j] + total_count);
			}
		}
	}
}

int main(void) {
	scanf("%d", &N);

	int end, cost;
	for (int i = 1; i <= N; i++) {
		scanf("%d %d", &end, &cost);
		dp[i][i + end - 1] = cost; // i일차에 할당된 일들. i+end-1 은 시작일 + 마감일 -1 이다. ex) 3일 치는 1,2,3이므로 -1이 필요
	}

	for (int i = 1; i <= N; i++) {
		for (int j = i; j <= N; j++) {
			if (dp[i][j]) {
				dfs(j, dp[i][j]);
			}
		}
	}

	cout << answer << endl;
	return 0;
}

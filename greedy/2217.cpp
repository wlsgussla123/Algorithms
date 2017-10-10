/*
	작성자 : 박진현
	문제 : 백준 2217번 문제, 로프
*/

#include <stdio.h>
#include <algorithm>

using namespace std;

int max(int a, int b) {
	return a > b ? a : b;
}

int main(void) {
	int N;
	int rope[100000] = { 0, };
	int dp[100000] = { 0, };

	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &rope[i]);
	}

	sort(rope, rope + N);
	for (int i = 0; i < N; i++) {
		for(int j=i; j< N; j++) {
			dp[i] += rope[i];
		}
	}

	int maxTemp = dp[0];
	for (int i = 1; i < N; i++) {
		maxTemp = max(maxTemp, dp[i]);
	}

	printf("%d\n", maxTemp);
	
	return 0;
}

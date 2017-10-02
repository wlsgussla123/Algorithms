/*
	작성자: 박진현
	문제: 백준 11053, 가장 긴 증가하는 부분 수열문제
	일자 : 17.10.02 

	느낀점 : 문제를 보고 90분 가량 다른 방식으로 접근을 하다가 포기했다. 구글링을 통해 DP로 접근할 수 있다는 것을 깨달았다..
	문제를 많이 풀어보자.
*/

#include <stdio.h>

int main(void) {
	int N;
	int sequence[1000] = { 0, };
	int DP[1000] = { 0, };
	int len = 0;

	scanf("%d", &N);

	for (int i = 1; i <= N; i++) {
		scanf("%d", &sequence[i]);
	}

	for (int i = 1; i <= N; i++) {
		int max = 0;
		for (int j = 0; j < N; j++) {
			if (sequence[i] > sequence[j]) {
				if (max < DP[j])
					max = DP[j];
			}
		}

		DP[i] = max + 1;
		if (len < DP[i]) {
			len = DP[i];
		}
	}

	printf("%d\n", len);

	return 0;
}

/*
	작성자 : 박진현
	문제 : 백준 6603, 로또 문제

	설명 : 백트랙킹을 이용한 알고리즘.
*/

#include <stdio.h>

int num[13] = { 0, };
int visit[13] = { 0, };
int N;

void backtracking(int index, int cnt) {
	if (cnt == 6) {
		for (int i = 0; i < N; i++) {
			if (visit[i]) {
				printf("%d ", num[i]);
			}
		}
		putchar('\n');
	}
	else {
		for (int i = index + 1; i < N; i++) {
			visit[i] = 1;
			backtracking(i, cnt + 1);
			visit[i] = 0;
		}
	}
}

int main(void) {
	int cnt = 1;

	scanf("%d", &N);
	while (N != 0) {
		for (int i = 0; i < N; i++) {
			scanf("%d", &num[i]);
		}

		for (int i = 0; i < N; i++) {
			visit[i] = 1;
			backtracking(i, cnt);
			visit[i] = 0;
		}

		// 다음 케이스를 위한 초기화
		for (int i = 0; i < 13; i++) {
			visit[i] = num[i] = 0;
		}
		putchar('\n');
		scanf("%d", &N);
	}

	return 0;
}

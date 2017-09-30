/*
	작성자 : 박진현
	문제 : 크랙킹 코딩 인터뷰 1-7.
*/

#include <stdio.h>

using namespace std;

int map[100][100];

int main(void) {
	int M, N;

	scanf("%d %d", &M, &N);
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (!map[i][j]) {
				for (int k = 0; k < N; k++) {
					map[i][k] = 0;
				}
				break;
			}
		}
	}

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			printf("%d ", map[i][j]);
		}
		putchar('\n');
	}

	return 0;
}

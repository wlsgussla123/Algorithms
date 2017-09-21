/*
	작성자: 박진현
	문제 : 백준 2606번, 웜 바이러스 문제
*/

#include <stdio.h>
#include <stdlib.h>

int ** computers;
int * visit;

int vCount;
int eCount;

int cnt = 0;

void warmVirus(int start) {
	visit[start] = 1;
	cnt++;

	for (int i = 0; i < vCount; i++) {
		if (computers[start][i] == 1 && visit[i] == 0) {
			warmVirus(i);
		}
	}
}

int main(void) {
	/*
		0 1 0 0 1 0 0
		1 0 1 0 1 0 0
		0 1 0 0 0 0 0
		0 0 0 0 0 0 1
		1 1 0 0 0 1 0
		0 0 0 0 1 0 0
		0 0 0 1 0 0 0
	*/
	int row, col;

	scanf("%d", &vCount); // 7
	scanf("%d", &eCount); // 6

	computers = (int **)malloc(sizeof(int *) * vCount);
	visit = (int *)malloc(sizeof(int) * vCount);

	for (int i = 0; i < vCount; i++) {
		computers[i] = (int *)malloc(sizeof(int) * vCount);
	}

	for (int i = 0; i < vCount; i++) {
		visit[i] = 0;
	}

	for (int i = 0; i < eCount; i++) {
		scanf("%d %d", &row, &col);
		computers[row - 1][col - 1] = 1; // index에 맞춰주기 위하여 -1
		computers[col - 1][row - 1] = 1;
	}

	warmVirus(0);
	printf("%d\n", cnt-1);

	return 0;
}

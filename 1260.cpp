
/*
작정자 : 박진현
문제 : 백준 1260번, DFS와 BFS
*/

#include <stdio.h>
#include <stdlib.h>
#include <queue>

using namespace std;

int ** map;
int * dfsVisit;
int * bfsVisit;

int vCount;
int eCount;
int flag = 0;

queue<int> Queue;

/*
0 1 1 1
1 0 0 1
1 0 0 1
1 1 1 0
*/

void DFS(int start) {
	dfsVisit[start] = 1;

	printf("%d ", start + 1);

	for (int i = 0; i < vCount; i++) {
		if (map[start][i] == 1 && dfsVisit[i] == 0) {
			DFS(i);
		}
	}
}

void BFS(int start) {
	bfsVisit[start] = 1;

	printf("%d ", start + 1);

	for (int i = 0; i < vCount; i++) {
		if (map[start][i] == 1 && bfsVisit[i] == 0) {
			Queue.push(i); // 2,3
		}
	}

	while (!Queue.empty()) {
		int temp = Queue.front();
		Queue.pop();

		if (bfsVisit[temp] == 0) {
			BFS(temp);
		}
	}
}

int main(void) {
	int start;
	int row, col;

	scanf("%d", &vCount); // 정점
	scanf("%d", &eCount); // 간선
	scanf("%d", &start); // 시작점
	start = start - 1;

	bfsVisit = (int *)malloc(sizeof(int) * vCount);
	dfsVisit = (int *)malloc(sizeof(int) * vCount);

	map = (int **)malloc(sizeof(int *) * vCount);
	for (int i = 0; i < vCount; i++) {
		map[i] = (int *)malloc(sizeof(int) * vCount);
	}

	for (int i = 0; i < eCount; i++) {
		scanf("%d %d", &row, &col);
		map[row - 1][col - 1] = 1;
		map[col - 1][row - 1] = 1;
	}

	for (int i = 0; i < vCount; i++) {
		bfsVisit[i] = 0;
		dfsVisit[i] = 0;
	}

	DFS(start);
	putchar('\n');

	BFS(start);
	putchar('\n');

	return 0;
}

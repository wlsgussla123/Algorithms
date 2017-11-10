/*
 *	작성자 : 박진현
 *	문제 : 백준 14889번문제 (팀짜기)
 */
#include <iostream>
#include <stdlib.h>
#include <algorithm>

using namespace std;

int N;
int **map;
int *visit;

int answer = 987654321;

void input() {
	cin >> N;
}

void drawMap() {
	map = (int **)malloc(sizeof(int *) * N);
	//visit = (int **)malloc(sizeof(int *) * N);
	visit = (int *)malloc(sizeof(int) * N);

	for (int i = 0; i < N; i++) {
		map[i] = (int *)malloc(sizeof(int) * N);
		//visit[i] = (int *)malloc(sizeof(int) * N);
	}
}

void inputMap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
			//visit[i][j] = 0;
			visit[i] = 0;
		}
	}
}

void printMap() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << map[i][j] << " ";
		}
		cout << endl;
	}
}

void init() {
	input();
	drawMap();
	inputMap();
}

// 시너지 계산
int teamPower(int * team) {
	int power = 0;
	int len = N / 2;

	for (int i = 0; i < len; i++) {
		for (int j = i + 1; j < len; j++) {
			power += map[team[i]][team[j]];
			power += map[team[j]][team[i]];
		}
	}

	return power;
}

void makeB() {
	int * teamA = (int *)malloc(sizeof(int) * N/2);
	int * teamB = (int *)malloc(sizeof(int) * N/2);
	int a = 0, b = 0;

	// 방문은 A팀 아니면 B팀
	for (int i = 0; i < N; i++) {
		if (visit[i]) {
			teamA[a++] = i;
		}
		else {
			teamB[b++] = i;
		}
	}
	
	int aPower = teamPower(teamA);
	int bPower = teamPower(teamB);
	int diff = aPower > bPower ? aPower - bPower : bPower - aPower;

	if (answer > diff) answer = diff;

	free(teamA);
	free(teamB);
}

void makeA(int v, int cnt) {
	if (N / 2 == cnt) {
		makeB();
	}
	else {
		for (int i = v; i < N; i++) {
			if (!visit[i]) {
				visit[i] = 1;
				makeA(i, cnt + 1);
			}
		}
	}

	visit[v] = 0;
}

int main(void) {
	init();
	makeA(0,0); // 나를 주축으로 팀 구성

	cout << answer << endl;

	return 0;	
}

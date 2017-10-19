#include <iostream>
#include <string>
#include <queue>

using namespace std;

int map[1000][1000] = { 0, };
queue<pair<int, int>> q;
int answer = 0;

int endLine;
int curRow;
int curCol;
int curDir = 2; // 첨에는 오른쪽으로

bool result = false;

// 갈 방향 정해주자. (서 동 북 남)
int checkDir(int turn) {
	if (curDir == 1 && turn == 1) {
		return 4;
	}
	else if (curDir == 1 && turn == 2) {
		return 3;
	}
	else if (curDir == 2 && turn == 1) {
		return 3;
	}
	else if (curDir == 2 && turn == 2) {
		return 4;
	}
	else if (curDir == 3 && turn == 1) {
		return 1;
	}
	else if (curDir == 3 && turn == 2) {
		return 2;
	}
	else if (curDir == 4 && turn == 1) {
		return 1;
	}
	else if (curDir == 4 && turn == 2) {
		return 2;
	}
}

void snake(int go, int dir) {
	//while (!q.empty()) {
	//int go = q.front().first;
	//int turn = q.front().second;
	//q.pop();

	// 서쪽
	if (curDir == 1) {
		for (int i = 1; i <= go; i++) {
			answer++;
			if (curCol - go < 0 || curCol - go > endLine || map[curRow][curCol - i]) {
				//cout << "west" << endl;
				//cout << answer << endl;
				result = true;
				break;
			}
			map[curRow][curCol - i] = 1;
		}
		curCol = curCol - go;
	}
	// 동쪽
	else if (curDir == 2) {
		for (int i = 1; i <= go; i++) {
			answer++;
			if (curCol + i < 0 || curCol + i > endLine || map[curRow][curCol + i]) {
				//cout << "east" << endl;
				//cout << answer << endl;
				result = true;
				break;
			}
			map[curRow][curCol + i] = 1;
		}
		curCol = curCol + go;
	}
	// 북쪽
	else if (curDir == 3) {
		for (int i = 1; i <= go; i++) {
			answer++;
			if (curRow - i < 0 || curRow - i > endLine || map[curRow - i][curCol]) {
				//cout << "north" << endl;
				//cout << answer << endl;
				result = true;
				break;
			}
			map[curRow - i][curCol] = 1;
		}
		curRow = curRow - go;
	}
	// 남쪽
	else if (curDir == 4) {
		for (int i = 1; i <= go; i++) {
			answer++;
			if (curRow + i < 0 || curRow + i > endLine || map[curRow + i][curCol]) {
				//cout << "south" << endl;
				//cout << answer << endl;
				result = true;
				break;
			}
			map[curRow + i][curCol] = 1;
		}
		curRow = curRow + go;
	}

	curDir = checkDir(dir);
	//}
}

int main(void) {
	string L;
	getline(cin, L);

	endLine = atoi(L.c_str()) * 2; // <= endLine
	curRow = atoi(L.c_str()); // atoi(str.c_str()) && to_string(number)
	curCol = atoi(L.c_str());

	// input 부분
	int N;
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		int go, dir;
		char turn;

		scanf("%d %c", &go, &turn);
		if (turn == 'L') {
			dir = 1;
		}
		else {
			dir = 2;
		}

		//q.push({ go, dir });
		if (!result) {
			snake(go, dir);
		}
	}

	cout << answer << endl;

	return 0;
}

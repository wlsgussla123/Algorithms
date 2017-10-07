/*
	작성자 : 박진현
	문제 : 카카오 1차 채용테스트 6번

	설명 : DFS로 풀어야 할 것 같은데 DFS 적용을 어떻게 할지 감이 안 잡힌다..
	아무쪼록 코드가 매우 더러운 건 사실. 개선이 필요하다

	하지만, 1~6번까지 풀었다는 것에 만족은 조금은 한다.
*/

#include <stdio.h>
#include <vector>
#include <utility>

using namespace std;

int M, N;
char map[31][31];
vector<pair<int, int>> v;

// 동,남,대각
int dir[3][2] = { {0, 1}, {1,0}, {1,1}};
int answer = 0;

// 2x2 형태로 붙어있는 좌표를 추출
void removeBlock(int row, int col) {
	bool flag = true;

	for (int i = 0; i < 3; i++) {
		int x = row + dir[i][0];
		int y = col + dir[i][1];

		// 범위 안이라면,
		if (x >= 0 && x < M && y >= 0 && y < N) {
			// map이 0이면 지워진 블록이기 때문에 체킹을 할 필요가 없다.
			if (map[row][col] == map[x][y] && map[row][col] != '0') {
				flag = true;
			}
			else {
				flag = false;
				break;
			}
		}
		else {
			flag = false;
			break;
		}
	}

	if (flag) {
		v.push_back(make_pair(row, col));

		for (int i = 0; i < 3; i++) {
			int x = row + dir[i][0];
			int y = col + dir[i][1];

			v.push_back(make_pair(x, y));
		}
	}
}

// 좌표를 추출하여 붙어있는 블록 제거
void remove() {
	for (int i = 0; i < v.size(); i++) {
		map[v[i].first][v[i].second] = '0';
		//answer++; // 블록 제거될 때마다 답은 증가
	}
	
	v.clear(); // 좌표 다 꺼냈으므로 초기화
}

// 블록이 제거되면 밑으로 땡겨야지
void relocateMap() {
	bool flag = true;

	// 블록 내리기
	while (flag) {
		// 블록이 없으면 하나씩 내려
		for (int i = 0; i < M - 1; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i + 1][j] == '0') {
					map[i + 1][j] = map[i][j];
					map[i][j] = '0';
				}
			}
		}

		flag = false;

		// 블록밑에 블록이 있었다면 이 블록은 안 내려가고 있었음. 이것을 찾는 flag 있다면 다시 반복문 돌린다.
		for (int i = 0; i < M - 1; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != '0' && map[i + 1][j] == '0') {
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}
	}
}

int main(void) {
	for (int i = 0; i < 31; i++) {
		for (int j = 0; j < 31; j++) {
			map[i][j] = '0';
		}
		map[i][30] = '\0';
	}

	scanf("%d %d", &M, &N);

	for (int i = 0; i < M; i++) {
		scanf("%s", &map[i]);
	}

	bool gameFlag = true;
	while (gameFlag) {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				removeBlock(i, j);
			}
		}

		if (!v.size()) {
			gameFlag = false;
		}

		remove(); // 블록 제거
		relocateMap(); // 제거된 블록을 채우기
	}
	
	//for (int i = 0; i < M; i++) {
	//	for (int j = 0; j < N; j++) {
	//		printf("%c", map[i][j]);
	//	}
	//	putchar('\n');
	//}

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] == '0')
				answer++;
		}
	}

	printf("%d\n", answer);

	return 0;
}

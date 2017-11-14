#include <iostream>

using namespace std;

int N;
int map[65][65] = { 0, };

void quadtree(int li, int lj, int ri, int rj) {
	int origin = map[li][lj];
	bool isSame = true;

	for (int i = li; i <= ri; i++) {
		if (!isSame) break;
		for (int j = lj; j <= rj; j++) {
			if (origin != map[i][j]) {
				isSame = false;
				break;
			}
		}
	}

	if (!isSame) {
		cout << "(";
		quadtree(li, lj, (ri + li) / 2, (rj + lj) / 2);
		quadtree(li, (lj + rj) / 2 + 1, (ri + li) / 2, rj);
		quadtree((li + ri) / 2 + 1, lj, ri, (rj + lj) / 2);
		quadtree((li + ri) / 2 + 1, (lj + rj) / 2 + 1, ri, rj);
		cout << ")";
	}
	else {
		if (origin) {
			cout << "1";
		}
		else {
			cout << "0";
		}
	}
}

void input() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
		}
	}
}

int main(void) {
	int N; cin >> N;
	for (int i = 0; i < N; ++i)for (int j = 0; j < N; ++j)scanf("%1d", &map[i][j]);
	quadtree(0, 0, N-1, N-1);

	return 0;
}

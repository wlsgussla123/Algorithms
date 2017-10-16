#include <iostream>
#include <queue>

using namespace std;

int main(void) {
	int T;
	int N, M, v, r;
	queue<pair<int, int>> q;
	priority_queue<int> pq;

	scanf("%d", &T);

	while (T--) {
		r = 0;

		scanf("%d %d", &N, &M);
		for (int i = 0; i < N; i++) {
			scanf("%d", &v);
			q.push({ v, i }); // 값 인덱스
			pq.push(v);
		}

		while (q.size()) {
			int value = q.front().first;
			int index = q.front().second;
			q.pop();

			if (pq.top() == value) {
				r++;
				pq.pop();
				if (index == M) break;
			}
			else {
				q.push({ value, index });
			}
		}
		printf("%d\n", r);
		
		while (!q.empty()) {
			q.pop();
		}

		while (!pq.empty()) {
			pq.pop();
		}
	}

	return 0;
}

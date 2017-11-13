#include <iostream>

using namespace std;

typedef long long int LL;

#define MAX_N 500000

int N;
int input[MAX_N];
int sortArr[MAX_N];

LL mergeSort(int left, int right) {
	if (left == right) return 0;

	int mid = (left + right) / 2;
	LL ret = mergeSort(left, mid) + mergeSort(mid + 1, right);

	int idx = 0, leftIdx = left, rightIdx = mid+1;

	while (leftIdx <= mid || rightIdx <= right) {
		// 왼쪽 정렬은 무조건 오른쪽 정렬보다 작아야 하며, 만약 그렇지 않으면 inverse가 발생하므로 +1이 됨
		if (leftIdx <= mid && (rightIdx > right || input[leftIdx] <= input[rightIdx])) {
			sortArr[idx++] = input[leftIdx++];
		}
		else {
			ret += mid - leftIdx + 1; // right가 큰 것은 swap이 발생하는 것이므로
			//ret++;	// ret++가 안 되는 이유는 leftIdx가 더 작은 것일텐데 이 이후의 left 범주 안에 있는 값들은 자동으로 swap이 일어나는 것이기 때문
			sortArr[idx++] = input[rightIdx++];
		}
	}

	// 정렬되게 바꿔주기
	for (int i = 0; i < right - left + 1; i++)
		input[left + i] = sortArr[i];

	return ret;
}

int main(void) {
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> input[i];
	}

	cout << mergeSort(0, N - 1) << endl;
		
	return 0;
}

#include <stdio.h>
#include <vector>

using namespace std;

int r(int n) {
	int ret = 0;

	while (n > 0) {
		ret *= 10;
		ret += n % 10;
		n /= 10;
	}

	return ret;
}

int f(int n) {
	return n + r(n);
}

bool check(int n) {
	vector<int> digits;

	while (n > 0) {
		digits.push_back(n % 10);
		n /= 10;
	}

	for (int i = 0; i < digits.size() / 2; i++) {
		int left = i;
		int right = digits.size() - 1 - i;

		if (digits[left] != digits[right]) {
			return false;
		}
	}

	return true;
}

int main(void) {
	int n;

	scanf("%d", &n);

	for (int i = 1; i <= 4; i++) {
		n = f(n);

		if (i == 4 || n >= 10000) {
			printf("-1\n");
			break;
		}

		if (check(n)) {
			printf("%d\n", n);
			break;
		}
	}

	return 0;
}

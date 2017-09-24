/*
  작성자 : 박진현
  문제 : 2017 NHN 1번 참조
*/
#include <stdio.h>
#include <string>
#include <iostream>
#include <vector>
#include <string.h>

using namespace std;

vector<int> store;

void LRU(string sentence) {
	bool check = false;

	for (int i = 0; i < sentence.size(); i += 2) {
		if (store.size() < 3) {
			store.push_back(sentence[i]-'0');
		}
		else {
			for (int j = 0; j < store.size(); j++) {
				if (store[j] == sentence[i] - '0') {
					store.erase(store.begin() + j);
					store.push_back(sentence[i] - '0');

					check = true;
				}
			}

			if (!check) {
				printf("%d\n", store.front());
				store.erase(store.begin() + 0);
				store.push_back(sentence[i] - '0');
			}
			check = false;
		}
	}
}

int main(void) {
	string sentence;

	getline(cin, sentence);

	LRU(sentence);

	return 0;
}

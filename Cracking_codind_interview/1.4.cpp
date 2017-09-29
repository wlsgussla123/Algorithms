#include <stdio.h>
#include <string>
#include <iostream>

using namespace std;

bool anagram(string str1, string str2) {
	int j = str2.length() - 1;
	for (int i = 0; i < str1.length(); i++) {
		if (str1[i] != str2[j])
			return false;

		j--;
	}
	
	return true;
}

int main(void) {
	string str1;
	string str2;

	getline(cin, str1);
	getline(cin, str2);
	
	if (anagram(str1, str2))
		printf("anagram\n");
	else
		printf("no\n");

	return 0;
}

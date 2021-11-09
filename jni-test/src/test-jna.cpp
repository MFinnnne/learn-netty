//
// Created by MFine on 2021/11/7.
//


#include <iostream>

using namespace std;

void sayHello() {
    cout << "hello jna" << endl;
}

int testReturnInt() {
    return 1;
}

char const *returnArray(double d[], int a[], const char *str, int length) {
    for (int i = 0; i < length; i++) {
        cout << "from c++ value is d" << a[i] << endl;
    }
    cout << "from C++ str value: " << str << endl;
    while (*str) {
        cout << *str++ << endl;
    }
    d[0] = 1.0;
    d[1] = 4.0;
    char const *return_str = "hello from c++";
    return return_str;
}

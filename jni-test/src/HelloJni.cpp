//
// Created by Administrator on 2021/11/8.
//

//
// Created by MFine on 2021/11/6.
//

#include "HelloJni.h"
#include <jni.h>
#include <iostream>

using namespace std;

JNIEXPORT void JNICALL Java_HelloJni_sayHello
        (JNIEnv *, jobject) {
    cout << "hello fuck" << endl;
}
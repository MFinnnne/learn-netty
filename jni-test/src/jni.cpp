//
// Created by MFine on 2021/11/6.
//

#include "testprimitive_TestJNIPrimitive.h"
#include <jni.h>
#include <iostream>

using namespace std;

/*
 * Class:     testprimitive_TestJNIPrimitive
 * Method:    average
 * Signature: (II)D
 */
JNIEXPORT jdouble JNICALL Java_testprimitive_TestJNIPrimitive_average
        (JNIEnv *env, jobject obj, jint a, jint b) {
    cout << "In C++ " << a << "--" << b << endl;
    return ((jdouble) a + b) / 2.0;
}

/*
 * Class:     testprimitive_TestJNIPrimitive
 * Method:    transString
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_testprimitive_TestJNIPrimitive_transString
        (JNIEnv *env, jobject obj, jstring str) {
    const char *inCStr = (*env).GetStringUTFChars(str, nullptr);
    if (nullptr == inCStr) {
        return nullptr;
    }
    cout << "In c,the received string is: " << str << endl;

    // 释放内存
    env->ReleaseStringUTFChars(str, inCStr);
    string outCppStr;
    cout << "Enter a String";
    cin >> outCppStr;
    return env->NewStringUTF(outCppStr.c_str());
}

JNIEXPORT jdoubleArray JNICALL Java_testprimitive_TestJNIPrimitive_sumAndAverage
        (JNIEnv *env, jobject obj, jintArray jarry) {
    jint *inCarry = (*env).GetIntArrayElements(jarry, nullptr);
    if (nullptr == inCarry) {
        return nullptr;
    }
    jsize length = (*env).GetArrayLength(jarry);
    jint sum = 0;
    for (int i = 0; i < length; i++) {
        sum += inCarry[i];
    }
    jdouble avg = (jdouble) sum / length;
    (*env).ReleaseIntArrayElements(jarry, inCarry, 0);
    jdouble outCArray[] = {(jdouble) sum, avg};
    jdoubleArray p_array = (*env).NewDoubleArray(2);
    if (p_array == nullptr) {
        return nullptr;
    }
    (*env).SetDoubleArrayRegion(p_array, 0, 2, outCArray);
    return p_array;
}

JNIEXPORT void JNICALL Java_testprimitive_TestJNIPrimitive_modifyInstanceVariable
        (JNIEnv *env, jobject obj) {
    jclass p_jclass = (*env).GetObjectClass(obj);

    jfieldID p_number = (*env).GetFieldID(p_jclass, "number", "I");
    if (NULL == p_number) {
        return;
    }
    jint number = (*env).GetIntField(p_jclass, p_number);
    cout << "In c++ ,the int is " << number << endl;

    number = 99;
    (*env).SetIntField(obj, p_number, number);
    jfieldID messageId = (*env).GetFieldID(p_jclass, "message", "Ljava/lang/String;");
    if (messageId == NULL) {
        return;
    }
    auto message = (jstring) ((*env).GetObjectField(obj, messageId));
    const char *cStr = (*env).GetStringUTFChars(message, NULL);
    if (cStr == nullptr) {
        return;
    }

    cout << "In c++,the string is " << cStr << endl;
    (*env).ReleaseStringUTFChars(message, cStr);
    message = (*env).NewStringUTF("hello from c++");
    if (message == NULL) {
        return;
    }
    (*env).SetObjectField(obj, messageId, message);

    jfieldID sId = (*env).GetStaticFieldID(p_jclass, "staticNumber", "I");
    if (NULL == sId) {
        return;
    }
    jint sNumber = (*env).GetStaticIntField(p_jclass, sId);
    cout << "In c++ , sNumber is " << sNumber << endl;
    sNumber = 233;
    (*env).SetStaticIntField(p_jclass, sId, sNumber);
}

JNIEXPORT void JNICALL Java_testprimitive_TestJNIPrimitive_nativeMethod
        (JNIEnv *env, jobject obj) {
    jclass p_jclass = env->GetObjectClass(obj);
    jmethodID callMethodId = env->GetMethodID(p_jclass, "callback", "()V");
    if (callMethodId == NULL) {
        return;
    }
    cout << "callback() execute in c++" << endl;
    env->CallVoidMethod(obj, callMethodId);

    jmethodID callBackStr = env->GetMethodID(p_jclass, "callback", "(Ljava/lang/String;)V");
    jstring message = env->NewStringUTF("hello from c++");
    cout << " callBack(str) execute in c++:" << endl;
    env->CallVoidMethod(obj, callBackStr, message);

    jmethodID midCallBackAverage = env->GetMethodID(p_jclass,
                                                    "callbackAverage", "(II)D");
    if (NULL == midCallBackAverage) return;
    jdouble average = env->CallDoubleMethod(obj, midCallBackAverage, 2, 3);
    cout << "In C++, the average is " << average << endl;

    jmethodID midCallBackStatic = env->GetStaticMethodID(p_jclass,
                                                         "callbackStatic", "()Ljava/lang/String;");
    if (NULL == midCallBackStatic) return;
    jstring resultJNIStr = (jstring) env->CallStaticObjectMethod(p_jclass, midCallBackStatic);
    const char *resultCStr = env->GetStringUTFChars(resultJNIStr, NULL);
    if (NULL == resultCStr) return;
    cout << "In C++, the returned string is " << resultCStr << endl;
    env->ReleaseStringUTFChars(resultJNIStr, resultCStr);
}

JNIEXPORT jobject JNICALL Java_testprimitive_TestJNIPrimitive_getIntegerObject
        (JNIEnv *env, jobject obj, jint number) {
    jclass cls = (*env).FindClass("java/lang/Integer");

    // Get the Method ID of the constructor which takes an int
    jmethodID midInit = (*env).GetMethodID(cls, "<init>", "(I)V");
    if (NULL == midInit) return NULL;
    // Call back constructor to allocate a new instance, with an int argument
    jobject newObj = (*env).NewObject(cls, midInit, number);

    // Try runnning the toString() on this newly create object
    jmethodID midToString = (*env).GetMethodID(cls, "toString", "()Ljava/lang/String;");
    if (NULL == midToString) return NULL;
    jstring resultStr = (jstring) (*env).CallObjectMethod(newObj, midToString);
    const char *resultCStr = (*env).GetStringUTFChars(resultStr, NULL);
    cout << "In C++: the number is " << resultCStr;
    return newObj;
}

JNIEXPORT jobjectArray JNICALL Java_testprimitive_TestJNIPrimitive_sumAndAverageToo
        (JNIEnv *env, jobject thisObj, jobjectArray inJNIArray) {
    // Get a class reference for java.lang.Integer
    jclass classInteger = (*env).FindClass("java/lang/Integer");
    // Use Integer.intValue() to retrieve the int
    jmethodID midIntValue = (*env).GetMethodID(classInteger, "intValue", "()I");
    if (NULL == midIntValue) return NULL;

    // Get the value of each Integer object in the array
    jsize length = (*env).GetArrayLength(inJNIArray);
    jint sum = 0;
    int i;
    for (i = 0; i < length; i++) {
        jobject objInteger = (*env).GetObjectArrayElement(inJNIArray, i);
        jint value = (*env).CallIntMethod(objInteger, midIntValue);
        sum += value;
    }
    double average = (double) sum / length;
    cout << "In C++, the sum is " << sum << endl;
    cout << "In C++, the average is " << average << endl;

    // Get a class reference for java.lang.Double
    jclass classDouble = (*env).FindClass("java/lang/Double");

    // Allocate a jobjectArray of 2 java.lang.Double
    jobjectArray outJNIArray = (*env).NewObjectArray(2, classDouble, NULL);

    // Construct 2 Double objects by calling the constructor
    jmethodID midDoubleInit = (*env).GetMethodID(classDouble, "<init>", "(D)V");
    if (NULL == midDoubleInit) return NULL;
    jobject objSum = (*env).NewObject(classDouble, midDoubleInit, (double) sum);
    jobject objAve = (*env).NewObject(classDouble, midDoubleInit, average);
    // Set to the jobjectArray
    (*env).SetObjectArrayElement(outJNIArray, 0, objSum);
    (*env).SetObjectArrayElement(outJNIArray, 1, objAve);

    return outJNIArray;
}
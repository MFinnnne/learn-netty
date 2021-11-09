cmake_minimum_required(VERSION 3.20)
project(jni_test)


find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
include_directories(header)
add_subdirectory(src)

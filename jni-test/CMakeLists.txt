cmake_minimum_required(VERSION 3.20)
project(jni_test)

set(CMAKE_CXX_STANDARD 20)
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
include_directories(header)
add_subdirectory(src)

#include <stdio.h>
#include "com_cwww_natives_NativeDemo.h"

JNIEXPORT void JNICALL Java_com_cwww_natives_NativeDemo_sayHello
(JNIEnv *env, jclass obj) {
    printf("C Native Hello!\n");
}

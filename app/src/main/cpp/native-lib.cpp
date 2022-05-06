#include <jni.h>
#include <string>
#include "LinkEd.cpp"
#include "TreeTraverse.cpp"
#include "Graph.cpp"
extern "C" JNIEXPORT jstring JNICALL

Java_com_fx_note_airthmtic_ArithmeticActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_fx_note_airthmtic_ArithmeticActivity_testDem1(JNIEnv *env, jobject) {


    BTNode *BT;
    BT = (BTNode *) malloc(sizeof(BTNode));
    BT->data = 2;


    LOGD("2");
    LOGD("%d", BT->data);
//    LOGD(BT->data);
//     Sqlist sqlist;
//
//    for (int i = 0; i <maxSize ; ++i) {
//        sqlist.data[i]=i;
//        ++sqlist.length;
//    }
//    for (int i = 0; i <sqlist.length ; ++i) {
//        LOGD("%d",sqlist.data[i]);
//    }
//
//    insertElem(sqlist,33);
//    for (int i = 0; i <sqlist.length ; ++i) {
//        LOGD("%d",sqlist.data[i]);
//    }
//    LNode *lNodeA;
//    lNodeA = (LNode *) malloc(sizeof(LNode));
//    lNodeA->data = 0;
//
//    LNode *lNodeB;
//    lNodeB = (LNode *) malloc(sizeof(LNode));
//    lNodeB->data = 0;
//
//    LNode *lNodeD=lNodeA;
//    for (int i = 1; i < 10; i = i + 2) {
//        auto *lNodeP = (LNode *) malloc(sizeof(LNode));
//        lNodeP->data = i;
//        lNodeD->next = lNodeP;
//        lNodeD=lNodeP;
//    }
//    lNodeD->next=NULL;
//
//    link_print(lNodeA);
//
//
//    LNode *lNodeE=lNodeB;
//    for (int i = 2; i < 10; i = i + 2) {
//        auto *lNodeP = (LNode *) malloc(sizeof(LNode));
//        lNodeP->data = i;
//        lNodeE->next = lNodeP;
//        lNodeE=lNodeP;
//    }
//    lNodeE->next=NULL;
//
//    link_print(lNodeB);
//
//
//    LNode *lNodeC;
//    lNodeC = (LNode*) malloc(sizeof(LNode));
//
//    merge(lNodeA,lNodeB,lNodeC);
//
//    link_print(lNodeC);
//
//
//
//    findAndDelete(lNodeC,6);
//
//    link_print(lNodeC);







    std::string hello = "testDem1";
//     BTNode BT;

    return env->NewStringUTF(hello.c_str());
}




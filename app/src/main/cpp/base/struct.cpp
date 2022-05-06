//
// Created by 鑫冯 on 2021/8/22.
//

typedef struct BTNode {
    int data;
    struct BTNode *lchild;
    struct BTNode *rchild;

} BTNode;

#define maxSize 100
typedef struct {
    int data[maxSize];
    int length = 0;


} Sqlist;

typedef struct LNode {
    int data;
    LNode *next;
} LNode;

typedef struct DLNode {
    int data;
    DLNode *prior;
    DLNode *next;
} DLNode;

typedef struct TNode {
    char data;
    struct TNode *lchild;
    struct TNode *rchild;

} TNode, *Tree;

typedef struct ArcNode {

    int adjvex;                //该边所指向的结点的位置
    struct ArcNode  *nextrac;  //指向吓一条边的指针
    int info;
}ArcNode;

typedef  struct {
    char data;  //顶点信息
    ArcNode *firsttrac; //指向顶点第一条边的指针

}VNode;

typedef struct{
    VNode adjlist[maxSize];  //邻接表
    int n,e;     //顶点和边的信息

}AGraph;

typedef struct TKNode {
    int  key;
    struct TKNode *lchild;
    struct TKNode *rchild;

} TKNode, *TKree;


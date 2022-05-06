//
// Created by 鑫冯 on 2021/8/24.
// 链表 方法
//

#include "base/struct.cpp"
#include "base/Utils.cpp"

//找到一个data 是 x 的 并返货
int findElem(Sqlist sqlist, int x) {
    int i;
    for (i = 0; i < sqlist.length; ++i) {
        if (x < sqlist.data[i]) {
            return i;
        }
    }
    return i;
}


void insertElem(Sqlist &sqlist, int x) {
    int p, i;
    p = findElem(sqlist, x);

    for (i = sqlist.length - 1; i >= p; --i) {
        sqlist.data[i + 1] = sqlist.data[i];
    }

    sqlist.data[p] = x;
    ++sqlist.length;
}

//合并2个链表并 按照data 大小排序， 一直单个链表是递增的。
void merge(LNode *A, LNode *B, LNode *&C) {
    LNode *p = A->next;
    LNode *q = B->next;
    LNode *r;
    C = A;

    C->next = NULL;
    free(B);
    r = C;

    while (p != NULL && q != NULL) {
        if (p->data <= q->data) {
            r->next = p;
            p = p->next;
            r = r->next;
        } else {

            r->next = q;
            q = q->next;
            r = r->next;
        }


    }
    r->next = NULL;
    if (p != NULL) r->next = p;
    if (q != NULL) r->next = q;


}

void link_print(LNode *lNode) {
    LNode *s = lNode;
    while (s != NULL) {

        LOGD("%d", s->data);
        s = s->next;
    };

}

void printBTNode(BTNode *btNode) {
    LOGD("%d", btNode->data);
}


void print(struct LNode *pHead) {
    struct LNode *pTemp;/*定义指向一个学生信息结构体类型的临时指针*/
    int iIndex = 1;/*定义并出事哈变量iIndex，用来标识第几个学生（信息）*/
//    printf("总共%d个学生（信息）：\n",iCount);
    pTemp = pHead;/*指针得到首结点的地址*/
    while (pTemp != NULL)/*当临时指针不指向NULL时*/
    {
        printf("第%d信息：\n", iIndex);
        printf("data ：%d", pTemp->data); /*输出姓名*/
        pTemp = pTemp->next;/*移动临时指针到下一个结点*
        iIndex++;/*进行自加运算*/
    }
}

int findAndDelete(LNode *C, int x) {
    LNode *p = C; //找出值等于x 的对象
    LNode *q; //找出值等于x 的对象
    while (p->next != NULL) {

        if (p->next->data == x)
            break;
        p = p->next;
    }
    if (p->next == NULL)
        return 0;
    else {
        // 开始删除
        q = p->next;
        p->next = p->next->next;
        free(q);
        return 1;
    }
}

//采用尾插法创建双链表

void createDlistR(DLNode *&dlNode, int n) {
    DLNode *s, *r; //一个代表临时变量，一个用于新建节点。
    int i;
    dlNode = (DLNode *) malloc(sizeof(DLNode));
    dlNode->prior = NULL;
    dlNode->next = NULL;
    r = dlNode;
    for (int i = 0; i < n; ++i) {
        s = (DLNode *) malloc(sizeof(DLNode));
        s->data = i;
        r->next = s;
        s->prior = r;
        r = s;  //不管单链表还是双链表必须把新建节点 赋值  给临时变量。
    }
    r->next = NULL;

}

//找到x的节点停止并返回
DLNode *findNode(DLNode *c, int x) {
    DLNode *p = c->next;
    while (p != NULL) {
        if (p->data == x) {
            break;
        }
        p = p->next;

    }
    return p;
}

int num=0;






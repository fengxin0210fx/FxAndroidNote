//
// Created by 鑫冯 on 2021/9/5.
//

#include "base/Utils.cpp"

//先序遍历
void preorderFirst(TNode *tNode) {
    if (tNode != 0) {
        LOGD("tNode=     %s", tNode->data);
        preorderFirst(tNode->lchild);
        preorderFirst(tNode->rchild);
    }

}


//中序遍历
void preorderMid(TNode *tNode) {
    if (tNode != 0) {

        preorderMid(tNode->lchild);
        LOGD("tNode=     %s", tNode->data);
        preorderMid(tNode->rchild);
    }

}


//后序遍历
void preorderEnd(TNode *tNode) {
    if (tNode != 0) {
        preorderEnd(tNode->lchild);
        preorderEnd(tNode->rchild);
        LOGD("tNode=     %s", tNode->data);
    }

}

//获取二叉树深度
int getDepth(TNode *tNode) {
    int LD, RD;
    if (tNode == NULL) {
        return 0;
    } else {
        LD = getDepth(tNode->lchild);
        RD = getDepth(tNode->rchild);
        return (LD > RD ? LD : RD) + 1;
    }
}

//层次法遍历二叉树
void level(TNode *tNode) {
    //输出的位置， 节点数
    int front, rear;
    TNode *que[maxSize];
    front = rear = 0;
    TNode *q;
    if (tNode != NULL) {
        rear = (rear + 1) % maxSize;
        que[rear] = tNode;//根节点入栈
        while (front != rear) {
            front = (front + 1) / maxSize; // front 一直++ ，遍历所有节点，并查看子节点还有没有，有就入栈
            q = que[front]; //出队
            LOGD("tNode=     %s", q->data); //输出
            if (q->lchild != NULL) {
                rear = (rear + 1) % maxSize;
                que[rear] = q->lchild;

            }
            if (q->rchild != NULL) {
                rear = (rear + 1) % maxSize;
                que[rear] = q->rchild;
            }
        }
    }
}

//二叉树深度优先非递归实现------先序

void preorderNonrecursion(TNode *tNode) {
    if (tNode != NULL) {
        TNode *Stack[maxSize]; //定义一个栈
        int top = -1;
        TNode *p;
        Stack[++top] = tNode;    //根节点传入第一个
        while (top != -1) {
            p = Stack[top--];
            LOGD("tNode=     %s", p->data); //输出
            if (p->rchild != NULL) {
                Stack[++top] = p->rchild;
            }
            if (p->lchild != NULL) {
                Stack[++top] = p->lchild;
            }

        }
    }
}

//二叉树深度优先非递归实现------中序
void inorderNonrecursion(TNode *tNode) {
    if (tNode != NULL) {
        TNode *Stack[maxSize]; //定义一个栈
        int top = -1;
        TNode *p;
        p = tNode;

        while (top != -1 || p != NULL) {
            while (p != NULL) {
                Stack[++top] = p;  //先把所有的左孩子入栈
                p = p->lchild;
            }
            if (top != -1) {

                p = Stack[top--];  //最后入站的移除
                LOGD("tNode=     %s", p->data); //输出
                p = p->rchild;    //最后入站的右孩子
            }
        }
    }
}

//二叉树深度优先非递归实现------后序
void postNonrecursion(TNode *tNode) {
    if (tNode != NULL) {
        TNode *Stack1[maxSize]; //定义一个栈、
        TNode *Stack2[maxSize]; //定义一个栈
        int top1 = -1;
        int top2 = -1;

        TNode *p = NULL;
        Stack1[++top1] = tNode;

        while (top1 != -1) {
            p = Stack1[top1--];
            Stack2[++top2] = p;  //和先序的区别在于，先不输出结果，用另一个数组接收一下。然后后面遍历输出。
            if (p->lchild != NULL) {
                Stack1[++top1] = p->lchild;
            }
            if (p->rchild != NULL) {
                Stack1[++top1] = p->rchild;
            }
        }
        while (top2 != -1) {
            p = Stack2[top2--];
            LOGD("tNode=     %s", p->data); //输出
        }
    }
}

//2 叉排序数查找关键字
TKNode *BSTSearch(TKNode *bt, int key) {
    if (bt == NULL) {
        return NULL;
    } else {
        if (bt->key == key) {
            return bt;
        } else if (key < bt->key) {
            return BSTSearch(bt->lchild, key);
        } else {
            return BSTSearch(bt->rchild, key);
        }
    }
}

int BSTInsert(TKNode *&bt, int key) {
    if (bt == NULL) {
        bt = (TKNode *) malloc(sizeof(TKNode));
        bt->lchild = bt->rchild = NULL;
        bt->key = key;
        return 1;
    } else {
        if (key == bt->key) {
            return 0;
        } else if (key < bt->key) {
            BSTInsert(bt->lchild, key);
        } else {
            BSTInsert(bt->rchild, key);
        }
    }
}

void creatBST(TKNode *&bt, int key[], int n) {
    int i;
    bt = NULL;
    for (int i = 0; i < n; ++i) {
        BSTInsert(bt, key[i]);
    }
}



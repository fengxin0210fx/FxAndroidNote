//
// Created by 鑫冯 on 2021/9/9.
//

#include "base/Utils.cpp"
//#include "base/struct.cpp"


//邻接表为存储结构的图的深度优先算法
int visit[maxSize];

void DFS(AGraph *graph, int v) { //v是起点编号
    ArcNode *p;
    visit[v] = 1;
    LOGD("%d", v);
    p = graph->adjlist[v].firsttrac;
    while (p != NULL) {
     if (visit[p->adjvex]==0){
         DFS(graph,p->adjvex);
     }
     p=p->nextrac;

    }

}
package com.fx.note.airthmtic.sort;

import java.util.Arrays;

/**
 * @author Created by 冯鑫 on 2021/9/13 20:44.
 * @description
 */
public class SortDemo {

    //直接插入排序
    public static void InsertSort(int R[], int n) {
        int i, j, temp;
        for (i = 1; i < n; ++i) { //从1 开始原因是，先默认第一个是排好的。
            temp = R[i];
            j = i - 1;       //从到当前要排序的元素的前一个开始比较，一直到第一个。
            while (j >= 0 && temp < R[j]) {    //如果当前元素比 R[j] 小；
                R[j + 1] = R[j];               //把R[j]往后移动一个。
                --j; //一直到找到比它大的元素结束循环
            }
            R[j + 1] = temp; //把它放在它那个位置，因为它已经被赋值都后面一个了。
        }
    }
    // 时间复杂度是O(n²),空间复杂度是 O（1）； 基本操作是 R[j + 1] = R[j];
    //最好条件下，已经排好续了，内循环常量级，始终1,时间复杂度是O(n);



    //冒泡排序
    public static void BubbleSort(int R[], int n) {
        int i, j, temp, flag;
        for (i = n - 1; i > 1; i--) { //先把最大的数冒泡到最后，然后 i-- ，第二个大的数排到倒数第二个
            flag = 0;
            for (j = 1; j <= i; i++) {
                if (R[j - 1] > R[j]) {
                    temp = R[j];
                    R[j] = R[j - 1];
                    R[j - 1] = temp;
                    flag = 1;
                }
                if (flag == 0) { //如果第一趟没发生过交换说明是有序的不用继续循环
                    return;
                }
            }

        }
    }
    // 时间复杂度是O(n²),空间复杂度是 O（1）； 基本操作是内循环交换;
    //最好条件下，已经排好续了,时间复杂度是O(n);


    //希尔排序
    public void sheelSort(int[] a) {
        int len = a.length;//单独把数组长度拿出来，提高效率
        while (len != 0) {
            len = len / 2;
            for (int i = 0; i < len; i++) {//分组
                for (int j = i + len; j < a.length; j += len) {//元素从第二个开始
                    int k = j - len;//k为有序序列最后一位的位数
                    int temp = a[j];//要插入的元素
                    /*for(;k>=0&&temp<a[k];k-=len){
                        a[k+len]=a[k];
                    }*/
                    while (k >= 0 && temp < a[k]) {//从后往前遍历
                        a[k + len] = a[k];
                        k -= len;//向后移动len位
                    }
                    a[k + len] = temp;
                }
            }
        }
    }


    //快速排序  Arrays.sort() 快速排序;
    public static void QuickSort(int R[], int low, int high) {

//        Arrays.sort()


        int temp;
        int i = low;
        int j = high;
        if (low < high) {
            temp = R[low];
            while (i < j) {

                while (j > i && R[j] >= temp) {
                    --j;
                }
                if (i < j) {
                    R[i] = R[j];
                    ++i;
                }

                while (i < j && R[i] < temp) {
                    ++i;
                }
                if (i < j) {
                    R[j] = R[i];
                    --j;
                }
            }
            R[i] = temp;
            QuickSort(R, low, i - 1);
            QuickSort(R, i + 1, high);
        }
    }
    // 最坏情况下时间复杂度是O(n²),平均复杂度是O(n log2 n)
    //空间复杂度O(log2 n)；递归比较费空间



}

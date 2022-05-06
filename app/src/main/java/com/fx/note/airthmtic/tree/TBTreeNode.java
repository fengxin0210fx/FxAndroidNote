package com.fx.note.airthmtic.tree;

/**
 * @author Created by 冯鑫 on 2021/9/8 20:16.
 * @description
 */
public class TBTreeNode {
    int data;
    TBTreeNode left;
    TBTreeNode right;
    int ltag,rtag;

    public TBTreeNode() {
    }

    public TBTreeNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TBTreeNode getLeft() {
        return left;
    }

    public void setLeft(TBTreeNode left) {
        this.left = left;
    }

    public TBTreeNode getRight() {
        return right;
    }

    public void setRight(TBTreeNode right) {
        this.right = right;
    }

    public int getLtag() {
        return ltag;
    }

    public void setLtag(int ltag) {
        this.ltag = ltag;
    }

    public int getRtag() {
        return rtag;
    }

    public void setRtag(int rtag) {
        this.rtag = rtag;
    }

    @Override
    public String toString() {
        return "TBTreeNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                ", ltag=" + ltag +
                ", rtag=" + rtag +
                '}';
    }
}

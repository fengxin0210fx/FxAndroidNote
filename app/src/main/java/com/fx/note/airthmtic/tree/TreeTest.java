package com.fx.note.airthmtic.tree;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author Created by 冯鑫 on 2021/9/6 19:43.
 * @description
 */
public class TreeTest {
    public static final int[] TREE_VALUE = new int[]{1, 2, 3, 0, 4, 5, 0, 0, 6, 0, 0, 7, 0, 0, 8, 0, 9, 10, 0, 0, 0};
    private static final String TAG = "TreeTest";
    public TreeNode root = new TreeNode();
    public int index = 0;


    public TreeNode createTree(TreeNode node, int i) {
        if (0 == TREE_VALUE[i]) {
            return null;
        } else {
            node.setData(TREE_VALUE[i]);
        }

        TreeNode leftChild = new TreeNode();
        node.left = createTree(leftChild, ++index);
        TreeNode rightChild = new TreeNode();
        node.right = createTree(rightChild, ++index);

        return node;
    }

    public TBTreeNode createTbTree(TBTreeNode node, int i) {
        if (0 == TREE_VALUE[i]) {
            return null;
        } else {
            node.setData(TREE_VALUE[i]);
        }

        TBTreeNode leftChild = new TBTreeNode();
        node.left = createTbTree(leftChild, ++index);
        TBTreeNode rightChild = new TBTreeNode();
        node.right = createTbTree(rightChild, ++index);

        return node;
    }

    public void getNode(TreeNode root, int value) {

    }

    //递归遍历 先序 1,2,3,4,5,6,7,8,9  从上到下，从左到右 依次输出
    public void traverseInOrder(TreeNode root) {
        if (root != null) {
            LogUtils.dTag(TAG, root.getData() + "");
            traverseInOrder(root.left);
            traverseInOrder(root.right);
        }

    }

    //递归遍历 中序  3,5,4,6,2,7,1,8,10,9  左到右，先找左边的节点,直到没有左边节点，就输出该节点，然后找该节点右边节点，如果右边节点还有左节点继续找左边节点，直到左边没有就输出，然后回退上个节点输出 找右。
    public void traverseMidOrder(TreeNode root) {
        if (root != null) {

            traverseMidOrder(root.left);
            LogUtils.dTag(TAG, root.getData() + "");
            traverseMidOrder(root.right);
        }

    }

    //递归遍历 后序 5,6,4,3,7,2,10,9,8,1   从下到上， 左到右
    public void traverseEndOrder(TreeNode root) {
        if (root != null) {
            traverseEndOrder(root.left);
            traverseEndOrder(root.right);
            LogUtils.dTag(TAG, root.getData() + "");
        }
    }

    //递归获取深度
    public int getDeth(TreeNode root) {
        int DL = 0, RL = 0;
        if (root != null) {
//            LogUtils.dTag(TAG, root.getData() + "");
            DL = getDeth(root.left);
            RL = getDeth(root.right);
        } else {
            return 0;
        }
        return (Math.max(DL, RL)) + 1;
    }

    //非递归二叉树前序遍历
    public void traverseInOrder1(TreeNode root) {
        if (root != null) {
            TreeNode[] treeNodes = new TreeNode[10];
            int index = -1;
            TreeNode temp;
            treeNodes[++index] = root;
            while (index != -1) {
                temp = treeNodes[index--];  //如果每个节点都有 左和右 ,那每次index加一
                LogUtils.dTag(TAG, temp.getData() + "");
                if (temp.right != null) {
                    treeNodes[++index] = temp.right; //先加右，因为右后出栈
                }
                if (temp.left != null) {
                    treeNodes[++index] = temp.left;//后加左
                }
            }
        }
    }

    //非递归二叉树中序
    public void traverseMidOrder1(TreeNode root) {
        if (root != null) {
            TreeNode[] treeNodes = new TreeNode[10];
            int index = -1;
            TreeNode temp = root;
            while (index != -1 || temp != null) {
                while (temp != null) {    //先把所有的左加进去
                    treeNodes[++index] = temp;
                    temp = temp.left;
                }
                //当左边没有左了 就打印左  并获取右，回到上面继续找左
                if (index != -1) {
                    temp = treeNodes[index--];
                    LogUtils.dTag(TAG, temp.getData() + "");
                    temp = temp.right;
                }
            }
        }
    }

    //非递归二叉树后序
    public void traverseEndOrder1(TreeNode root) {
        if (root != null) {
            TreeNode[] treeNodes = new TreeNode[10];
            TreeNode[] treeNodeRet = new TreeNode[10];
            int index = -1;
            int index2 = -1;
            TreeNode temp;
            treeNodes[++index] = root;
            while (index != -1) {
                temp = treeNodes[index--];
                treeNodeRet[++index2] = temp;
                if (temp.left != null) {
                    treeNodes[++index] = temp.left; //先加左
                }
                if (temp.right != null) {
                    treeNodes[++index] = temp.right;//后加右
                }
            }
            while (index2 != -1) {
                temp = treeNodeRet[index2--];  //如果每个节点都有 左和右 ,那每次index加一
                LogUtils.dTag(TAG, temp.getData() + "");

            }
        }
    }

    //2叉树线索化
    void IntThread(TBTreeNode p, TBTreeNode pre) {
        if (p != null) {

            IntThread(p.left, pre);//递归，左子树线索化
            if (p.left == null) {  //建立当前结点的前驱线索
                p.left = pre;
                p.setLtag(1);
            }
            if (pre != null && pre.right == null) {
                pre.right = p;       //建立前驱结点的后继线索
                pre.rtag = 1;
            }

            pre = p;
            p=p.right;
            IntThread(p,pre); //递归。右子树线索化
        }


    }
}

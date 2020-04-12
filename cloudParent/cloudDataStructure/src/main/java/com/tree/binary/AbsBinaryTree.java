package com.tree.binary;

public abstract class AbsBinaryTree {

    public abstract Node find(int key);// 查找指定节点

    public abstract boolean update(int key, int value);

    public abstract void insert(int key, int value); // 插入节点

    public abstract boolean delete(int key); // 删除指定节点

    public abstract Node getDirectPostNode(Node delNode); // 得到待删除节点的直接后继节点

    public abstract void preOrder(Node rootNode); // 先序遍历树

    public abstract void inOrder(Node rootNode); // 中序遍历树

    public abstract void postOrder(Node rootNode); // 后序遍历树

}

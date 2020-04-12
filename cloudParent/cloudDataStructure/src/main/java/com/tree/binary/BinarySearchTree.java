package com.tree.binary;

/**
 * 二叉查找树又叫二叉搜索树、二叉排序树
 *
 * 二叉查找/搜索/排序树  BST  (binary search/sort tree)
 * 或者是一棵空树；
 * 或者是具有下列性质的二叉树：
 * （1）若它的左子树不空，则左子树上所有结点的值均小于它的根节点的值；
 * （2）若它的右子树上所有结点的值均大于它的根节点的值；
 * （3）它的左、右子树也分别为二叉排序树。
 * （4）没有键值相等的节点。
 */
public class BinarySearchTree extends AbsBinaryTree {

    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    @Override
    public Node find(int key) {
        Node currentNode = this.root;
        // currentNode.key和 key不等才需要循环
        while ((currentNode != null) && (currentNode.key != key)) {
            if (key < currentNode.key) {
                currentNode = currentNode.leftChild;
            } else if (key > currentNode.key) {
                currentNode = currentNode.rightChild;
            }
        }
        return currentNode;
    }

    @Override
    public boolean update(int key, int value) {
        // TODO Auto-generated method stub
        Node node = this.find(key);
        node.setValue(value);
        return true;
    }

    @Override
    public void insert(int key, int value) {
        // TODO Auto-generated method stub
        if (this.root == null) {
            this.root = new Node(key, value);
            return;
        }
        Node currentNode = this.root;
        Node parentNode = this.root;// 指向currentNode节点的父节点
        boolean isLeftChild = true;
        // 寻找插入位置
        while (currentNode != null) {
            parentNode = currentNode;
            if (key < currentNode.key) {
                currentNode = currentNode.leftChild;
                isLeftChild = true;
            } else if (key > currentNode.key) {
                currentNode = currentNode.rightChild;
                isLeftChild = false;
            } else {
                // 插入的节点key和二叉树中节点key相等无需插入
                // parentNode 和 currentNode两个引用指向相同Node对象，引用变量相等，只需要更改value
                break;
            }
        }
        // 插入节点
        if (parentNode != currentNode) {
            Node newNode = new Node(key, value);
            if (isLeftChild) {
                parentNode.leftChild = newNode;
            } else {
                parentNode.rightChild = newNode;
            }

        } else {
            // 如果待插入节点和二叉树中节点一样；则只要更改值
            currentNode.setValue(value);
        }
    }

    @Override
    public boolean delete(int key) {
        // TODO Auto-generated method stub
        Node currentNode = this.root;// 用来保存待删除节点
        Node parentNode = this.root;// 用来保存待删除节点的父亲节点
        boolean isLeftChild = true;// 用来保存待删除节点是父亲节点的左孩子还是右孩子
        // 寻找删除节点并记录删除节点的父节点以及他是父节点的左孩子还是右孩子
        while ((currentNode != null) && (currentNode.key != key)) {
            parentNode = currentNode;
            if (key < currentNode.key) {
                currentNode = currentNode.leftChild;
                isLeftChild = true;
            } else {
                currentNode = currentNode.rightChild;
                isLeftChild = false;
            }
        }
        if (currentNode == null) return false;// 空树
        // 要删除的节点为叶子节点，删除的第一种情况
        if ((currentNode.leftChild == null) && (currentNode.rightChild == null)) {
            if (currentNode == this.root) {
                this.root = null;
            } else if (isLeftChild) {
                parentNode.leftChild = null;
            } else {
                parentNode.rightChild = null;
            }
            // 要删除的节点只有左孩子 第二种情况
        } else if ((currentNode.rightChild == null) && (currentNode.leftChild != null)) {
            if (currentNode == this.root) {
                this.root = currentNode.leftChild;
            } else if (isLeftChild) {
                parentNode.leftChild = currentNode.leftChild;
            } else {
                parentNode.rightChild = currentNode.leftChild;
            }
            // 要删除的节点只有右孩子 第三种情况
        } else if ((currentNode.leftChild == null) && (currentNode.rightChild != null)) {
            if (currentNode == this.root) {
                this.root = currentNode.rightChild;
            } else if (isLeftChild) {
                parentNode.leftChild = currentNode.rightChild;
            } else {
                parentNode.rightChild = currentNode.rightChild;
            }
        } // 最后一种情况，待删除节点既有左子树又有右子树
        else {
            // 将待删除节点的右子树最小节点赋值给删除节点的key,value，那么删除后新的二叉树也是二叉排序树
            // 思路：删除右子树中key值最小的节点，并返回，然后用这个节点的值赋值删除节点的key和value
            // 右子树中key最小的节点一定不含左子树,所以删除这个key最小的节点一定是属于叶子节点或者只有右子树的节点
            Node directPostNode = this.getDirectPostNode(currentNode);
            currentNode.key = directPostNode.key;
            currentNode.value = directPostNode.value;
        }
        return true;
    }
    // 得到待删除节点的直接后继节点
    @Override
    public Node getDirectPostNode(Node delNode) {
        // TODO Auto-generated method stub
        // 方法作用为得到待删除节点的直接后继节点

        Node parentNode = delNode;// 用来保存待删除节点的直接后继节点的父亲节点
        Node direcrPostNode = delNode;// 用来保存待删除节点的直接后继节点
        Node currentNode = delNode.rightChild;// 待删除节点右子树
        while (currentNode != null) {
            parentNode = direcrPostNode;
            direcrPostNode = currentNode;
            currentNode = currentNode.leftChild;
        }
        if (direcrPostNode != delNode.rightChild) {// 从树中删除此直接后继节点
            parentNode.leftChild = direcrPostNode.rightChild;// 后继节点的父节点指向后继节点的右孩子
            direcrPostNode.rightChild = null;// 直接后继节点右孩子为空
        }
        return direcrPostNode;// 返回此直接后继节点
    }

    @Override
    public void preOrder(Node rootNode) {
        // TODO Auto-generated method stub
        if (rootNode != null) {
            System.out.println(rootNode.key + " " + rootNode.value);
            this.preOrder(rootNode.leftChild);
            this.preOrder(rootNode.rightChild);
        }
    }

    @Override
    public void inOrder(Node rootNode) {
        // TODO Auto-generated method stub
        if (rootNode != null) {
            this.inOrder(rootNode.leftChild);
            System.out.println(rootNode.key + " " + rootNode.value);
            this.inOrder(rootNode.rightChild);
        }
    }

    @Override
    public void postOrder(Node rootNode) {
        // TODO Auto-generated method stub
        if (rootNode != null) {
            this.postOrder(rootNode.leftChild);
            this.postOrder(rootNode.rightChild);
            System.out.println(rootNode.key + " " + rootNode.value);
        }
    }

    public static void main(String[] args){
        // TODO Auto-generated method stub
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(6, 6);// 插入操作,构造图一所示的二叉树
        tree.insert(3, 3);
        tree.insert(14, 14);
        tree.insert(16, 16);
        tree.insert(10, 10);
        tree.insert(9, 9);
        tree.insert(13, 13);
        tree.insert(11, 11);
        tree.insert(12, 12);
        System.out.println("删除前中序遍历结果，结果是一个递增有序序列");
        tree.inOrder(tree.getRoot());// 中序遍历操作
        tree.update(12, 200);
        System.out.println("更新节点值中序遍历结果  key=12的值");
        tree.inOrder(tree.getRoot());
        System.out.println("删除节点10之后遍历结果");

        tree.delete(10);// 删除操作
        tree.inOrder(tree.getRoot());

    }
}

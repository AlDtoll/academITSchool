package ru.academits.tolmachev.mytree;

public class MyTree<T extends Comparable> {

    private int number = 0;
    private TreeNode<T> root = null;

    public int getNumber() {
        return number;
    }

    public void addNode(T node) {
        TreeNode<T> newNode = new TreeNode<>(node);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode<T> currentNode = root;

            while (currentNode.getValue() != null) {
                if (node.compareTo(currentNode.getValue()) < 0) {
                    if (currentNode.getLeft() != null) {
                        currentNode = currentNode.getLeft();
                    } else {
                        currentNode.setLeft(newNode);
                        break;
                    }
                } else {
                    if (currentNode.getRight() != null) {
                        currentNode = currentNode.getRight();
                    } else {
                        currentNode.setRight(newNode);
                        break;
                    }
                }
            }
        }
        number++;
    }

    public boolean findNode(T node) {
        if (root == null) {
            return false;
        }
        TreeNode<T> currentNode = root;
        while (currentNode.getValue() != null) {
            if (node.compareTo(currentNode.getValue()) == 0) {
                return true;
            }
            if (node.compareTo(currentNode.getValue()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean removeNode(T node) {
        if (!this.findNode(node)) {
            return false;
        }
        TreeNode<T> currentNode = root;
        TreeNode<T> parent = new TreeNode<>();
        boolean rightTransfer = false;
        while (currentNode.getValue() != null) {
            if (node.compareTo(currentNode.getValue()) == 0) {
                if (currentNode == root) {
                    TreeNode<T> rootOfRightSubTree = currentNode.getRight();
                    TreeNode<T> rootOfLeftSubTree = currentNode.getLeft();
                    currentNode = currentNode.getRight();
                    while (currentNode.getLeft() != null) {
                        parent = currentNode;
                        currentNode = currentNode.getLeft();
                    }
                    parent.setLeft(null);
                    root = currentNode;
                    currentNode.setRight(rootOfRightSubTree);
                    currentNode.setLeft(rootOfLeftSubTree);
                } else if (currentNode.getLeft() == null && currentNode.getRight() == null) {// leaf
                    if (rightTransfer) {
                        parent.setRight(null);
                    } else {
                        parent.setLeft(null);
                    }
                } else if (currentNode.getLeft() == null || currentNode.getRight() == null) {// 1 children
                    if (rightTransfer) {
                        parent.setRight(currentNode.getRight() != null ? currentNode.getRight() : currentNode.getLeft());
                    } else {
                        parent.setLeft(currentNode.getRight() != null ? currentNode.getRight() : currentNode.getLeft());
                    }
                } else { // 2 children
                    TreeNode<T> parentOfSubTree = parent;
                    TreeNode<T> rootOfRightSubTree = currentNode.getRight();
                    TreeNode<T> rootOfLeftSubTree = currentNode.getLeft();
                    currentNode = currentNode.getRight();
                    while (currentNode.getLeft() != null) {
                        parent = currentNode;
                        currentNode = currentNode.getLeft();
                    }
                    parent.setLeft(null);
                    if (rightTransfer) {
                        parentOfSubTree.setRight(currentNode);
                    } else {
                        parentOfSubTree.setLeft(currentNode);
                    }
                    currentNode.setRight(rootOfRightSubTree);
                    currentNode.setLeft(rootOfLeftSubTree);
                }
                break;
            }
            if (node.compareTo(currentNode.getValue()) < 0) {
                if (currentNode.getLeft() != null) {
                    parent = currentNode;
                    rightTransfer = false;
                    currentNode = currentNode.getLeft();
                }
            } else {
                if (currentNode.getRight() != null) {
                    parent = currentNode;
                    rightTransfer = true;
                    currentNode = currentNode.getRight();
                }
            }
        }
        number--;
        return true;
    }

}

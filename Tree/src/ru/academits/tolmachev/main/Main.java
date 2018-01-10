package ru.academits.tolmachev.main;

import ru.academits.tolmachev.mytree.MyTree;

public class Main {

    public static void main(String[] args) {

        System.out.println("Программа демонстрирует использование бинарного дерева");

        MyTree<Integer> myTree = new MyTree<>();
        System.out.println("Количество элементов: " + myTree.getNumber());
        myTree.addNode(20);
        myTree.addNode(0);
        myTree.addNode(10);
        myTree.addNode(15);
        myTree.addNode(5);
        myTree.addNode(-10);
        myTree.addNode(-15);
        myTree.addNode(-5);
        myTree.addNode(40);
        myTree.addNode(50);
        myTree.addNode(55);
        myTree.addNode(45);
        myTree.addNode(30);
        myTree.addNode(25);
        myTree.addNode(35);
        System.out.println("Количество элементов: " + myTree.getNumber());
        Integer nodeForSearch = 25;
        System.out.println("Узел со значением " + nodeForSearch + " есть? " + myTree.findNode(nodeForSearch));
        Integer nodeForRemove = 20;
        System.out.println("Узел со значением " + nodeForRemove + " был удален? " + myTree.removeNode(nodeForRemove));
        System.out.println("Количество элементов: " + myTree.getNumber());
        nodeForSearch = nodeForRemove;
        System.out.println("Узел со значением " + nodeForSearch + " есть? " + myTree.findNode(nodeForSearch));

    }
}

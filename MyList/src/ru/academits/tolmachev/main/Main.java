package ru.academits.tolmachev.main;

import ru.academits.tolmachev.mylist.ListItem;
import ru.academits.tolmachev.mylist.MyList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Программа демонстрирует использование односвязного списка");

        MyList<Integer> myList = new MyList<>();
        ListItem<Integer> e0 = new ListItem<Integer>(3);
        ListItem<Integer> e1 = new ListItem<Integer>(9);
        ListItem<Integer> e2 = new ListItem<Integer>(7);
        ListItem<Integer> e3 = new ListItem<Integer>(22);
        ListItem<Integer> e4 = new ListItem<Integer>(5);
        ListItem<Integer> e5 = new ListItem<Integer>(11);
        ListItem<Integer> e6 = new ListItem<Integer>(14);

        myList.addToBegin(e0);
        myList.addToBegin(e1);
        myList.addToBegin(e2);
        myList.addToBegin(e3);
        myList.addToBegin(e4);
        myList.addToBegin(e5);
        myList.addToBegin(e6);
        System.out.println("Список " + myList);

        int index = 2;
        System.out.println("Длинна списка " + myList.getLength());
        System.out.println("Значение по " + index + " индексу: " + myList.getValue(index));
        System.out.println("Установка по " + index + " индексу. Старое значение " + myList.setValue(index, 333));
        System.out.println("Новое значение по " + index + " индексу: " + myList.getValue(index));
        System.out.println("Список " + myList);
        System.out.println("Получение первого элемента. Его значение: " + myList.getFirstNode().getData());
        System.out.println();

        index++;
        System.out.println("Получение элемента по " + index + " индексу. Его значение: " + myList.getNode(index).getData());
        index++;
        System.out.println("Удаление элемента по " + index + " индексу. Его значение: " + myList.removeNode(index).getData());
        System.out.println("Список " + myList);
        ListItem<Integer> eToAdd = new ListItem<Integer>(101);
        myList.addNode(index, eToAdd);
        System.out.println("Добавление элемента со значением " + eToAdd.getData() + " по " + index + " индексу");
        System.out.println("Список " + myList);
        System.out.println("Удаление узла по зачению. Был узел со значением " + eToAdd.getData() + " удален? " + myList.removeNode(eToAdd));
        System.out.println("Список " + myList);
        System.out.println("Удаление первого элемента. Его значение было: " + myList.cutHead().getData());
        System.out.println("Список " + myList);
        System.out.println();

        System.out.println("Размер списка " + myList.getLength());
        index = 2;
        System.out.println("Вставка после элемента с индексом " + index);
        myList.addAfter(index, eToAdd);
        System.out.println("Список " + myList);
        System.out.println("Размер списка " + myList.getLength());
        index = 3;
        System.out.println("Удаление после элемента с индексом " + index);
        myList.removeAfter(index);
        System.out.println("Список " + myList);
        myList.reverseList();
        System.out.println("Повернутый список" + myList);
        MyList<Integer> copyOfMyList = myList.copyList();
        System.out.println("Копия списка " + copyOfMyList);
        copyOfMyList.reverseList();
        System.out.println("Оригинал списка " + myList);
        System.out.println("Копия списка " + copyOfMyList);
        System.out.println();

        System.out.println("Хэшкод " + myList.hashCode());
        System.out.println("Хэшкод " + copyOfMyList.hashCode());
        MyList<Integer> myList1 = new MyList<>();
        myList1.addToBegin(new ListItem<Integer>(11));
        myList1.addToBegin(new ListItem<Integer>(333));
        myList1.addToBegin(new ListItem<Integer>(22));
        myList1.addToBegin(new ListItem<Integer>(101));
        myList1.addToBegin(new ListItem<Integer>(3));
        System.out.println("Хэшкод " + myList1.hashCode());
        System.out.println("Сравнение списков. Список " + myList + " и " + myList1 + " равны? " + myList.equals(myList1));
        myList.setValue(3, null);
        myList1.setValue(3, null);
        System.out.println("А теперь? Список " + myList + " и " + myList1 + " равны? " + myList.equals(myList1));
        myList.setValue(2, null);
        System.out.println("Ну а теперь? Список " + myList + " и " + myList1 + " равны? " + myList.equals(myList1));

    }
}

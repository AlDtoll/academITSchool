package ru.academits.tolmachev.mylist;

import java.util.Objects;

public class MyList<T> {

    private int length = 0;
    private ListItem<T> head = null;

    public int getLength() {
        return length;
    }

    public ListItem<T> getFirstNode() {
        return head;
    }

    public T getValue(int index) {
        return this.getNode(index).getData();
    }

    public T setValue(int index, T value) {
        ListItem<T> item = this.getNode(index);
        T oldValue = item.getData();
        item.setData(value);
        return oldValue;
    }

    public ListItem<T> getNode(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы списка");
        }
        ListItem<T> item = head;
        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }
        return item;
    }

    public T removeNode(int index) {
        T oldValue;
        if (index == 0) {
            oldValue = this.cutHead().getData();
        } else {
            ListItem<T> previousItem = this.getNode(index - 1);
            ListItem<T> currentItem = previousItem.getNext();
            oldValue = currentItem.getData();
            ListItem<T> nextItem = currentItem.getNext();
            previousItem.setNext(nextItem);
            length--;
        }
        return oldValue;
    }

    public void addToBegin(ListItem<T> listItem) {
        if (head == null) {
            head = listItem;
        } else {
            listItem.setNext(head);
            head = listItem;
        }
        length++;

    }

    public void addNode(int index, T value) {
        ListItem<T> item = new ListItem<T>(value);
        if (index == 0) {
            this.addToBegin(item);
        } else {
            ListItem<T> previousItem = this.getNode(index - 1);
            ListItem<T> currentItem = previousItem.getNext();
            previousItem.setNext(item);
            item.setNext(currentItem);
            length++;
        }

    }

    public boolean removeNode(T value) {
        ListItem<T> item = head;
        for (int i = 0; i < length; i++) {
            if (value == item.getData()) {
                removeNode(i);
                return true;
            } else {
                item = item.getNext();
            }
        }
        return false;
    }

    public ListItem<T> cutHead() {
        ListItem<T> oldItem = head;
        if (head == null) {
            throw new IllegalStateException("Пустой массив");
        }
        head = head.getNext();
        length--;
        return oldItem;
    }

    public void addAfter(int index, T value) {
        if (index >= length) {
            throw new IllegalStateException("Не существует элемента для послевставки");
        }
        this.addNode(index + 1, value);
    }

    public void removeAfter(int index) {
        if (index >= length - 1) {
            throw new IllegalStateException("Нечего удалять");
        }
        this.removeNode(index + 1);
    }

    public void reverseList() {
        ListItem<T> previousItem = null;
        ListItem<T> item = head;
        while (item != null) {
            ListItem<T> nextItem = item.getNext();
            item.setNext(previousItem);
            previousItem = item;
            item = nextItem;
        }
        head = previousItem;
    }

    public MyList<T> copyList() {
        MyList<T> newList = new MyList<>();
        if (length == 0) {
            return newList;
        }
        ListItem<T> item = new ListItem<>(head.getData());
        newList.head = item;
        ListItem<T> sourceItem = head.getNext();
        newList.length++;
        while (sourceItem != null) {
            ListItem<T> targetItem = new ListItem<>(sourceItem.getData());
            item.setNext(targetItem);
            item = targetItem;
            sourceItem = sourceItem.getNext();
            newList.length++;
        }
        return newList;
    }

    public String toString() {
        if (length == 0) {
            return ("[]");
        }
        StringBuilder str = new StringBuilder();
        str.append("[");
        ListItem<T> item = head;
        for (int i = 0; i < length - 1; i++) {
            str.append(item.getData()).append(", ");
            item = item.getNext();
        }
        str.append((item.getData()))
                .append("]");
        return str.toString();
    }

    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        ListItem<T> item = head;
        for (int i = 0; i < length; i++) {
            hash = prime * hash + ((item != null ? item.getData() : null) != null ? item.getData().hashCode() : 0);
            item = (item != null ? item.getData() : null) != null ? item.getNext() : null;
        }
        return hash;
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        MyList<T> m = (MyList<T>) o;
        if (this.length != m.length) {
            return false;
        }
        ListItem<T> item = head;
        ListItem<T> itemM = m.head;
        for (int i = 0; i < length; i++) {
            if (!Objects.equals(item.getData(), itemM.getData())) {
                return false;
            } else {
                item = item.getNext();
                itemM = itemM.getNext();
            }
        }
        return true;
    }

}




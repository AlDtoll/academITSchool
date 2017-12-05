package ru.academits.tolmachev.mylist;

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
        T oldValue = this.getValue(index);
        this.getNode(index).setData(value);
        return oldValue;
    }

    public ListItem<T> getNode(int index) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы списка");
        }
        ListItem<T> item = head;
        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }
        return item;
    }

    public ListItem<T> removeNode(int index) {
        ListItem<T> oldNode = this.getNode(index);
        if (index == 0) {
            this.cutHead();
        } else {
            ListItem<T> previousItem = this.getNode(index - 1);
            ListItem<T> currentItem = previousItem.getNext();
            ListItem<T> nextItem = currentItem.getNext();
            previousItem.setNext(nextItem);
            length--;
        }
        return oldNode;
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

    public void addNode(int index, ListItem<T> listItem) {
        if (index == 0) {
            this.addToBegin(listItem);
        } else {
            ListItem<T> previousItem = this.getNode(index - 1);
            ListItem<T> currentItem = previousItem.getNext();
            previousItem.setNext(listItem);
            listItem.setNext(currentItem);
            length++;
        }
    }

    public boolean removeNode(ListItem<T> listItem) {
        ListItem<T> item = head;
        for (int i = 0; i < length; i++) {
            if (listItem.getData() == item.getData()) {
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

    public void addAfter(int index, ListItem<T> listItem) {
        if (index >= length) {
            throw new IllegalStateException("Не существует элемента для послевставки");
        }
        this.addNode(index + 1, listItem);
    }

    public void removeAfter(int index) {
        if (index >= length - 1) {
            throw new IllegalStateException("Нечего удалять");
        }
        this.removeNode(index + 1);
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

}




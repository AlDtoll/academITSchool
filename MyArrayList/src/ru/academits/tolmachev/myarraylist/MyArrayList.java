package ru.academits.tolmachev.myarraylist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements List<E> {

    @SuppressWarnings("unchecked")
    private E[] items = (E[]) new Object[10];
    private int length = 0;

//    public MyArrayList() {
//
//    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(E element) {
        if (length >= items.length) {
            increaseCapacity();
        }
        items[length] = element;
        ++length;
        return true;
    }

    private void increaseCapacity() {
        E[] old = items;
        items = (E[]) new Object[old.length * 2];
        System.arraycopy(old, 0, items, 0, old.length);
    }

    @Override
    public boolean remove(Object o) {

        return false;
    }

    @Override
    public boolean addAll(Collection <? extends E> c) {
        for (E item: c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        if (index > length) {
            throw new IllegalArgumentException("Выход за пределы массива");
        } else {
            return items[index];
        }
    }

    @Override
    public E set(int index, E element) {
        if (index > length) {
            throw new IllegalArgumentException("Выход за пределы массива");
        } else {
            E temp = items[index];
            items[index] = element;
            return temp;
        }
    }

    @Override
    public void add(int index, E element) {
        if (index > length) {
            throw new IllegalArgumentException("Выход за пределы массива");
        } else {
            if (index < length - 1) {
                System.arraycopy(items, index + 1, items, index, length - index - 1);
            }
            --length;
        }

    }

    @Override
    public E remove(int index) {
        if (index > length) {
            throw new IllegalArgumentException("Выход за пределы массива");
        } else {
            E temp = items[index];
            if (index < length - 1) {
                System.arraycopy(items, index + 1, items, index, length - index - 1);
            }
            --length;
            return temp;
        }
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public E[] toArray(Object[] a) {
        return (E[]) new Object[length];
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < length - 1; i++) {
            str.append(this.items[i]).append(", ");
        }
        str.append(this.items[length - 1])
                .append("]");
        return str.toString();
    }
}

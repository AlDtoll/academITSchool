package ru.academits.tolmachev.myarraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {

    @SuppressWarnings("unchecked")
    private E[] items = (E[]) new Object[10];
    private int length = 0;

    public MyArrayList() {

    }

    @Override
    public int size() {
        return length;
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

    @Override
    public void add(int index, E element) {
        if (index > length - 1) {
            throw new IllegalArgumentException("Выход за пределы массива");
        } else {
            System.arraycopy(items, index, items, index + 1, length - index);
            items[index] = element;
            ++length;
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E item : c) {
            add(item);
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > length - 1) {
            throw new IllegalArgumentException("Выход за пределы массива");
        } else {
            if (items.length < length + c.size()) {
                E[] old = items;
                items = (E[]) new Object[length + c.size()];
                System.arraycopy(old, 0, items, 0, old.length);
            }
            System.arraycopy(items, index, items, index + c.size(), length - index);
            int i = index;
            for (E item : c) {
                items[i] = item;
                i++;
            }
            length += c.size();
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            items[i] = null;
        }
        length = 0;
    }

    @Override
    public boolean contains(Object o) {
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (items[i] == o || (o == null && items[i] == null)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        MyArrayList<E> e = (MyArrayList<E>) o;
        return (this.length == e.length) && Arrays.equals(this.items, e.items);
    }

    @Override
    public E get(int index) {
        if (index > length - 1) {
            throw new IllegalArgumentException("Выход за пределы массива");
        } else {
            return items[index];
        }
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(this.items);
        return hash;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (items[i] == o) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {// поставить 1.8
            private int currentElement = 0;

            @Override
            public boolean hasNext() {
                return (currentElement < length) && items[currentElement] != null;
            }

            @Override
            public E next() {
                return items[currentElement++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public E remove(int index) {
        if (index > length - 1) {
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
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
            --length;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }


    @Override
    public E set(int index, E element) {
        if (index > length - 1) {
            throw new IllegalArgumentException("Выход за пределы массива");
        } else {
            E temp = items[index];
            items[index] = element;
            return temp;
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (items[i] == o) {
                return i;
            }
        }
        return -1;
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
    public boolean containsAll(Collection c) {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray(Object[] a) {
        return (E[]) new Object[length];
    }

    public String toString() {
        if (length == 0) {
            throw new IllegalArgumentException("Пустой массив");
        }
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < length - 1; i++) {
            str.append(this.items[i]).append(", ");
        }
        str.append(this.items[length - 1])
                .append("]");
        return str.toString();
    }

    @SuppressWarnings("unchecked")
    private void increaseCapacity() {
        E[] old = items;
        items = (E[]) new Object[old.length * 2];
        System.arraycopy(old, 0, items, 0, old.length);
    }

}

package ru.academits.tolmachev.myarraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {

    @SuppressWarnings("unchecked")
    private E[] items = (E[]) new Object[10];
    private int length = 0;
    private int modCount = 0;

    public MyArrayList() {
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть положительным числом");
        }
        items = (E[]) new Object[capacity];
    }

    public void trimToSize() {
        if (length == 0) {
            throw new IndexOutOfBoundsException("Пустой массив");
        }
        if (length < items.length) {
            items = Arrays.copyOf(items, length);
        }
    }

    public boolean ensureCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть положительным числом");
        }
        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity);
            return true;
        }
        return false;
    }

    @Override
    public boolean add(E element) {
        if (length >= items.length) {
            increaseCapacity();
        }
        items[length] = element;
        ++length;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        System.arraycopy(items, index, items, index + 1, length - index);
        items[index] = element;
        ++length;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(length, c);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        if (items.length < length + c.size()) {
            items = Arrays.copyOf(items, length + c.size());
        }
        System.arraycopy(items, index, items, index + c.size(), length - index);
        int i = index;
        boolean isChanged = false;
        for (E item : c) {
            items[i] = item;
            i++;
            isChanged = true;
        }
        if (isChanged) {
            length += c.size();
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            items[i] = null;
        }
        length = 0;
        modCount++;
    }

    @Override
    public boolean contains(Object o) {
        return length != 0 && indexOf(o) != -1;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
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
        if (this.length != e.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Objects.equals(items[i], e.items[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public E get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        E temp = items[index];
        items[index] = element;
        return temp;
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
        for (int i = 0; i < length; i++)
            if (Objects.equals(o, items[i])) {
                return i;
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
            private int currentModCount = modCount;

            @Override
            public boolean hasNext() {
                return currentElement < length;
            }

            @Override
            public E next() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (currentElement == length) {
                    throw new NoSuchElementException("Выход за пределы массива");
                }
                return items[currentElement++];
            }

            public void remove() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                MyArrayList.this.remove(currentElement);
                currentModCount = modCount;
            }
        };
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListIterator listIterator(int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        return new ListIterator<E>() {
            private int currentElement = index;
            private int currentModCount = modCount;

            @Override
            public boolean hasNext() {
                return currentElement < length;
            }

            @Override
            public E next() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (currentElement == length) {
                    throw new NoSuchElementException("Конец коллекции - нет следующего элемента");
                }
                return items[currentElement++];
            }

            @Override
            public boolean hasPrevious() {
                return currentElement > 0;
            }

            @Override
            public E previous() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (currentElement == 0) {
                    throw new NoSuchElementException("Начало коллеции - нет предыдушего элемента");
                }
                return items[currentElement--];
            }

            @Override
            public int nextIndex() {
                return currentElement + 1;
            }

            @Override
            public int previousIndex() {
                return currentElement - 1;
            }

            @Override
            public void remove() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                MyArrayList.this.remove(currentElement);
            }

            @Override
            public void set(E e) {
                if (currentElement >= length || currentElement < 0) {
                    throw new IndexOutOfBoundsException("Выход за пределы массива");
                }
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                items[currentElement] = e;


            }

            @Override
            public void add(E e) {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                MyArrayList.this.add(currentElement, e);
            }
        };
    }

    @Override
    public E remove(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        E temp = items[index];
        System.arraycopy(items, index + 1, items, index, length - index - 1);
        --length;
        modCount++;
        return temp;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
            --length;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean isChanged = false;
        for (Object item : c) {
            for (int i = 0; i < length; i++) {
                if (Objects.equals(items[i], item)) {
                    remove(i);
                    isChanged = true;
                }
            }
        }
        if (isChanged) {
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean isChanged = false;
        int newIndex = 0;
        for (int i = 0; i < length; i++) {
            for (Object item : c) {
                if (Objects.equals(items[i], item)) {
                    items[newIndex] = items[i];
                    newIndex++;
                    isChanged = true;
                }
            }
        }
        length = newIndex;
        if (isChanged) {
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return length;
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public E[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < length) {
            return (T[]) Arrays.copyOf(items, length);
        }
        a = (T[]) Arrays.copyOf(items, length);
        if (a.length > length) {
            a[length] = null;
        }
        return a;
    }

    public String toString() {
        if (length == 0) {
            return ("[]");
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

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

}

package ru.academits.tolmachev.mylist;

public class MyList<T> {

    private int lengh = 0;
    private T data;
    private MyList<T> next;

    public int getLengh(){
        return lengh;
    }


}

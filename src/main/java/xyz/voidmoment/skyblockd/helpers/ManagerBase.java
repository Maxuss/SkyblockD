package xyz.voidmoment.skyblockd.helpers;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ManagerBase<T> {

    public ArrayList<T> contains;
    public void addContain(T item){
        contains.add(item);
    }
    public void addContains(T[] items)
    {
        contains.addAll(Arrays.asList(items));
    }

    public abstract void register();

    public ManagerBase(){
        contains = new ArrayList<>();
    }

}

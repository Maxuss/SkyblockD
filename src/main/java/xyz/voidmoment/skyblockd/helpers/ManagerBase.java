package xyz.voidmoment.skyblockd.helpers;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ManagerBase<TContain> {

    public ArrayList<TContain> contains;
    public void addContain(TContain item){
        contains.add(item);
    }
    public void addContains(TContain[] items)
    {
        contains.addAll(Arrays.asList(items));
    }

    public abstract void register();

    public ManagerBase(){
        contains = new ArrayList<>();
    }

}

package xyz.voidmoment.skyblockd.helpers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main class to be inherited by all managers
 * @param <T> type of contain to store inside manager
 * @author maxus
 */
public abstract class ManagerBase<T> {

    /**
     * Contains of manager's provided type
     */
    public ArrayList<T> contains;

    /**
     * Adds a single contain item to contains
     * @param item Contain to be added
     */
    public void addContain(T item){
        contains.add(item);
    }

    /**
     * Adds multiple contains items to contains
     * @param items items to be added
     */
    public void addContains(T[] items)
    {
        contains.addAll(Arrays.asList(items));
    }

    /**
     * Registers all contains and stores them.<br/>
     * The only method that should be overriden.
     */
    public abstract void register();

    /**
     * Main constructor
     */
    public ManagerBase(){
        contains = new ArrayList<>();
    }

}

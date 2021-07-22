package space.maxus.skyblockd.util;

public class Singleton<E, U> {
    private E first;
    private U second;

    public E getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void setFirst(E first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public Singleton(E e, U u) {
        first = e;
        second = u;
    }

    public Singleton() {}
}

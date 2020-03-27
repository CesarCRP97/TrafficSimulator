package simulator.misc;

public class Pair<T1, T2> {
    private final T1 _first;
    private final T2 _second;

    public Pair(T1 first, T2 second) {
        _first = first;
        _second = second;
    }

    public T1 getFirst() {
        return _first;
    }

    public T2 getSecond() {
        return _second;
    }

}

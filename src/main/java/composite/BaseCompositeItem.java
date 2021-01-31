package composite;

public interface BaseCompositeItem<T> {
    void add(T item);
    T get(int index);
    String getText();
}

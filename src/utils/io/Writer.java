package utils.io;


public interface Writer<T> {

    void write(final String path, T t);

}

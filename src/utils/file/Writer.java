package utils.file;


import io.reactivex.Observable;

public interface Writer<T> {

    void write(final String path, T t);

}

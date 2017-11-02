package utils.file;


import io.reactivex.Observable;

public interface Reader<T> {

    Observable<T> read(final String path);

}

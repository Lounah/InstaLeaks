package utils.file;

import domain.model.User;
import io.reactivex.Observable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FileReader implements Reader<User> {

    @Override
    public Observable<User> read(String path) {

        return Observable.create(emitter -> {
            String[] credentialsData;
            BufferedReader br = null;
            try {
                String curr_line = "";
                br = new BufferedReader(new java.io.FileReader(path), 1000 * 8192);
                while ((curr_line = br.readLine()) != null) {
                    credentialsData = curr_line.split(" ");
                    User currentUser = new User(credentialsData[0], credentialsData[1], credentialsData[2], credentialsData[3]);
                    emitter.onNext(currentUser);
                }

                if (br.readLine() == null) {
                    emitter.onComplete();
                }

            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

        });
    }
}

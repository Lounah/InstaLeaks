package utils.io;

import domain.model.Account;
import io.reactivex.Observable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader implements Reader<Account> {

    @Override
    public Observable<Account> read(String path) {

        return Observable.create(emitter -> {
            String[] credentialsData;
            BufferedReader br = null;
            try {
                String curr_line = "";
                br = new BufferedReader(new java.io.FileReader(path), 1000 * 8192);
                while ((curr_line = br.readLine()) != null) {
                    credentialsData = curr_line.split(" ");
                    Account currentAccount = new Account(credentialsData[0], credentialsData[1], credentialsData[2], credentialsData[3]);
                    emitter.onNext(currentAccount);
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

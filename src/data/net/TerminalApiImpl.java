package data.net;

import data.exception.SpamingBehaviourError;
import data.exception.UsernameIsNotAvailable;
import domain.model.Account;
import domain.model.Response;
import domain.net.TerminalApi;
import io.reactivex.Observable;
import java.io.*;

public class TerminalApiImpl implements TerminalApi {

    public TerminalApiImpl() {

    }

    @Override
    public Observable<Account> createAccount(String name, String username, String email, String password) {
        return Observable.create(emitter -> {

        String[] command = {"python3", "/Users/lounah/Documents/programming/InstaLeaks/instaMaker.py",
                name, username, email, password};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();

        StringBuffer output = new StringBuffer();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((line = bufferedReader.readLine()) != null) {
            output.append(line);
        }
        process.waitFor();

        System.out.println(output.toString());

        switch (output.toString()) {
            case ("Error:This username isn't available. Please try another."):
                emitter.onError(new UsernameIsNotAvailable());
                emitter.onComplete();
                break;
            case ("Account has been created successfully"):
                emitter.onNext(new Account(name, username, email, password));
                emitter.onComplete();
                break;
            case ("Instagram blocks your IP due to spamming behaviour."):
                emitter.onError(new SpamingBehaviourError());
                emitter.onComplete();
                break;
        }

        });
    }

    @Override
    public Observable<Response> subscribeAccount(Account account1, Account account2) {
        return Observable.create(emitter -> {

            String[] command = {"php", "/Users/lounah/Documents/programming/InstaLeaks/subscribeAccount.php",
                    account1.getUsername(), account1.getPassword(), account2.getUsername()};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            StringBuffer output = new StringBuffer();
            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line);
            }
            process.waitFor();

            System.out.println(output.toString());

            Response response = Response.withCode(200);

            emitter.onNext(response);


        });
    }

    @Override
    public Observable<Response> postPhotoToAccount(Account user, String path) {
        return Observable.create(emitter -> {

            String[] command = {"php", "/Users/lounah/Documents/programming/InstaLeaks/postPhoto.php",
                    user.getUsername(), user.getPassword(), path};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            StringBuffer output = new StringBuffer();
            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line);
            }
            process.waitFor();

            System.out.println(output.toString());

            Response response = Response.withCode(200);

            emitter.onNext(response);


        });
    }


}

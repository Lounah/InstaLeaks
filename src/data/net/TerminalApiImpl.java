package data.net;

import data.exception.FailedToCreateUserException;
import data.exception.SpamingBehaviourError;
import data.exception.UsernameIsNotAvailable;
import domain.model.User;
import domain.net.TerminalApi;
import io.reactivex.Observable;
import java.io.*;

public class TerminalApiImpl implements TerminalApi {

    public TerminalApiImpl() {

    }

    @Override
    public Observable<User> createAccount(String name, String username, String email, String password) {
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

        switch (output.toString()) {
            case ("Error:This username isn't available. Please try another."):
                emitter.onError(new UsernameIsNotAvailable());
                break;
            case ("Account has been created successfully"):
                emitter.onNext(new User(name, username, email, password));
                break;
            case ("Instagram blocks your IP due to spamming behaviour."):
                emitter.onError(new SpamingBehaviourError());
                break;
        }

        });
    }

}

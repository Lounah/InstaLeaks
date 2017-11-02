package data.net;

import data.exception.FailedToCreateUserException;
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
//            try {
//                User user = new User(name, username, email, password);
//
//
////                String command = "python3 instamaker.py " + user.toString();
////                ProcessBuilder processBuilder = new ProcessBuilder(command);
////                processBuilder.directory(new File("/Users/lounah/Documents/programming/InstaLeaks"));
////                Process process = processBuilder.start();
//
//              //  String[] cmd = {"python3", "Users/lounah/Documents/programming/InstaLeaks/instaMaker.py " + user.toString()};
//             //   process.waitFor();
//
//                //Process process = Runtime.getRuntime().exec(cmd);
//                StringBuffer output = new StringBuffer();
//
//                String[] command = {"python3", "/Users/lounah/Documents/programming/InstaLeaks/instaMaker.py", name, username, email, password};
//
//                Process process = Runtime.getRuntime().exec(command);
//
//                BufferedReader reader =
//                        new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//                String line;
//                while ((line = reader.readLine())!= null) {
//                    output.append(line + "\n");
//                }
//
//
////                if (output.toString().equals("Account has been created successfully")) {
////                    emitter.onNext(user);
////                    System.out.print("ON NEXT" + " " + user.toString());
////                } else {
////                    System.out.println(output);
////                    emitter.onError(new FailedToCreateUserException());
////                    System.out.print("ON ERROR \n");
////                }
//                emitter.onNext(user);
//
//                if (process != null) {
//                    process.waitFor();
//                }
//
//                process.destroy();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        String[] command = {"python3", "/Users/lounah/Documents/programming/InstaLeaks/instaMaker.py",
                " " + name, " " + username + " " + email + " " + password};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();

        System.out.println("process...");
        process.waitFor();

        StringBuffer output = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println("line: " + line);
            output.append(line);
        }

        });
    }

}

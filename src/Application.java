import data.net.TerminalApiImpl;
import data.repository.AccountRepositoryImpl;
import domain.interactor.CreateAccount;
import domain.interactor.DefaultObserver;
import domain.model.User;
import domain.net.TerminalApi;
import domain.repository.AccountRepository;
import io.reactivex.observers.DisposableObserver;
import utils.Constants;
import utils.UserProvider;
import utils.file.FileWriter;
import utils.file.Writer;


import java.util.List;

public class Application {

    private static final TerminalApi api;
    private static final AccountRepository repository;
    private static final CreateAccount createAccountProcess;
    private static final Writer<String> fileWriter;
    private static final List<User> users;

    static {
        api = new TerminalApiImpl();
        repository = new AccountRepositoryImpl(api);
        createAccountProcess = new CreateAccount(repository);
        users = UserProvider.provideUserList();
        fileWriter = new FileWriter();
    }

    public static void main(String... args) {



//        for (User user : users) {
////            createAccountProcess.execute(new AccountObserver(),
////                    CreateAccount.Params.forAccount(user.getName(),
////                            user.getUsername(),
////                            user.getEmail(),
////                            user.getPassword()));
//            System.out.println(user.toString());
//        }

        User user = users.get(2);
        createAccountProcess.execute(new AccountObserver(), CreateAccount.Params.forAccount(user.getName(), user.getUsername(), user.getEmail(), user.getPassword()));
    }


    private static final class AccountObserver extends DefaultObserver<User> {


        @Override
        public void onNext(User user) {
            System.out.println("ON NEXT");
            Application.fileWriter.write(Constants.CHECKED_USERS_FILE_PATH, user.toString() + "\n");
        }

        @Override
        public void onError(Throwable t) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onComplete() {
            dispose();
        }

    }

}

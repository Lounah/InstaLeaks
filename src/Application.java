import data.exception.SpamingBehaviourError;
import data.exception.UsernameIsNotAvailable;
import data.net.TerminalApiImpl;
import data.repository.AccountRepositoryImpl;
import domain.interactor.CreateAccount;
import domain.interactor.DefaultObserver;
import domain.model.User;
import domain.net.TerminalApi;
import domain.repository.AccountRepository;
import utils.Constants;
import utils.UserProvider;
import utils.io.FileWriter;
import utils.io.Writer;


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

        User user = users.get(4);
        createAccountProcess.execute(new AccountObserver(), CreateAccount.Params.forAccount(user.getName(), user.getUsername(), user.getEmail(), user.getPassword()));
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static final class AccountObserver extends DefaultObserver<User> {


        @Override
        public void onNext(User user) {
            Application.fileWriter.write(Constants.CHECKED_USERS_FILE_PATH, user.toString() + "\n");
        }

        @Override
        public void onError(Throwable t) {
            if (t instanceof SpamingBehaviourError) {
                try {
                    Thread.sleep(Constants.THREAD_SPAM_ERROR_WAIT_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (t instanceof UsernameIsNotAvailable) {

            }
        }

        @Override
        public void onComplete() {
            dispose();
        }

    }

}

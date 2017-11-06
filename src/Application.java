import com.sun.org.apache.regexp.internal.RE;
import data.exception.SpamingBehaviourError;
import data.exception.UsernameIsNotAvailable;
import data.net.TerminalApiImpl;
import data.repository.AccountRepositoryImpl;
import domain.interactor.CreateAccount;
import domain.interactor.DefaultObserver;
import domain.interactor.PostPhoto;
import domain.interactor.SubscribeAccount;
import domain.model.Account;
import domain.model.Response;
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
    private static final SubscribeAccount subscribeAccountProcess;
    private static final PostPhoto postPhotoProcess;
    private static final Writer<String> fileWriter;
    private static final List<Account> uncheckedAccounts;
    private static final List<Account> checkedAccounts;

    private static int THREAD_SLEEP_DURATION = 21000;

    private static void setThreadSleepDuration(int duration) {
        THREAD_SLEEP_DURATION = duration;
    }

    static {
        api = new TerminalApiImpl();
        repository = new AccountRepositoryImpl(api);
        createAccountProcess = new CreateAccount(repository);
        subscribeAccountProcess = new SubscribeAccount(repository);
        postPhotoProcess = new PostPhoto(repository);
        uncheckedAccounts = UserProvider.provideUserListFromFile(Constants.UNCHECKED_USERS_FILE_PATH);
        checkedAccounts = UserProvider.provideUserListFromFile(Constants.CHECKED_USERS_FILE_PATH);
        fileWriter = new FileWriter();
    }

    public static void main(String... args) {

//        Account account_to_subscribe = new Account("Максим Вишня", "moon_vishnya", "moonvishnya@gmail.com", "moonluna");
//
//        for (Account account : checkedAccounts) {
//            subscribeAccountProcess.execute(new SubscribeAccountObserver(),
//                    SubscribeAccount.Params.forSubscribing(account, account_to_subscribe));
//        }

//        for (Account account : uncheckedAccounts) {
//            createAccountProcess.execute(new AccountObserver(),
//                    CreateAccount.Params.forAccount(account.getName(), account.getUsername(), account.getEmail(), account.getPassword()));
//
//            try {
//                Thread.sleep(THREAD_SLEEP_DURATION);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//
//        for (Account account : checkedAccounts) {
//            postPhotoProcess.execute(new PostPhotoObserver(),
//                    PostPhoto.Params.forPosting(account, Constants.POST_PHOTO_PROFILE_IMAGE_PATH));
//        }

    }


    private static final class AccountObserver extends DefaultObserver<Account> {


        @Override
        public void onNext(Account account) {
            Application.fileWriter.write(Constants.CHECKED_USERS_FILE_PATH, account.toString());
        }

        @Override
        public void onError(Throwable t) {
            if (t instanceof SpamingBehaviourError) {

            }
            if (t instanceof UsernameIsNotAvailable) {

            }
        }

        @Override
        public void onComplete() {
            dispose();
        }

    }

    private static final class SubscribeAccountObserver extends DefaultObserver<Response> {

        @Override
        public void onNext(Response response) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }

    }

    private static final class PostPhotoObserver extends DefaultObserver<Response> {

        @Override
        public void onNext(Response response) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }

    }

}

package data.repository;

import domain.model.Account;
import domain.model.Response;
import domain.net.TerminalApi;
import domain.repository.AccountRepository;
import io.reactivex.Observable;

public class AccountRepositoryImpl implements AccountRepository {

    private final TerminalApi api;

    public AccountRepositoryImpl(final TerminalApi api) {
        this.api = api;
    }

    @Override
    public Observable<Account> createAccount(String name, String username, String email, String password) {
        return api.createAccount(name, username, email, password);
    }

    @Override
    public Observable<Response> subscribeAccount(Account user1, Account user2) {
        return api.subscribeAccount(user1, user2);
    }

    @Override
    public Observable<Response> postPhotoToAccount(Account user, String path) {
        return api.postPhotoToAccount(user, path);
    }

}

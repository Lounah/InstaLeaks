package data.repository;

import domain.model.User;
import domain.net.TerminalApi;
import domain.repository.AccountRepository;
import io.reactivex.Observable;

public class AccountRepositoryImpl implements AccountRepository {

    private final TerminalApi api;

    public AccountRepositoryImpl(final TerminalApi api) {
        this.api = api;
    }

    @Override
    public Observable<User> createAccount(String name, String username, String email, String password) {
        return api.createAccount(name, username, email, password);
    }
}

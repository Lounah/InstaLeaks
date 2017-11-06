package domain.interactor;

import domain.model.Account;
import domain.repository.AccountRepository;
import io.reactivex.Observable;

public class CreateAccount extends UseCase<Account, CreateAccount.Params> {

    private final AccountRepository repository;

    public CreateAccount(final AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    Observable<Account> buildUseCaseObservable(CreateAccount.Params params) {
        return repository.createAccount(params.name, params.username, params.email, params.password);
    }

    public static final class Params {

        private String name;
        private String username;
        private String email;
        private String password;

        private Params(final String name, final String username, final String email, final String password) {
            this.name = name;
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public static Params forAccount(final String name, final String username, final String email, final String password) {
            return new Params(name, username, email, password);
        }
    }
}

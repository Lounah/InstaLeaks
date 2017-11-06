package domain.interactor;

import domain.model.Account;
import domain.model.Response;
import domain.repository.AccountRepository;
import io.reactivex.Observable;

public class SubscribeAccount extends UseCase<Response, SubscribeAccount.Params> {


    private final AccountRepository repository;

    public SubscribeAccount(final AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    Observable<Response> buildUseCaseObservable(SubscribeAccount.Params params) {
        return repository.subscribeAccount(params.account1, params.account2);
    }

    public static final class Params {

        private Account account1;
        private Account account2;

        private Params(final Account account1, final Account account2) {
            this.account1 = account1;
            this.account2 = account2;
        }

        public static SubscribeAccount.Params forSubscribing(final Account account1, final Account account2) {
            return new SubscribeAccount.Params(account1, account2);
        }
    }

}

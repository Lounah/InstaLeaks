package domain.repository;


import domain.model.Account;
import domain.model.Response;
import io.reactivex.Observable;

public interface AccountRepository {

    Observable<Account> createAccount(final String name, final String username, final String email, final String password);

    Observable<Response> subscribeAccount(final Account user1, final Account user2);

    Observable<Response> postPhotoToAccount(final Account user, final String path);

}

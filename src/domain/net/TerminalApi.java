package domain.net;


import domain.model.Account;
import domain.model.Response;
import io.reactivex.Observable;

public interface TerminalApi {

    Observable<Account> createAccount(final String name, final String username, final String email, final String password);

    Observable<Response> subscribeAccount(final Account account1, final Account account2);

    Observable<Response> postPhotoToAccount(final Account user, final String path);

}

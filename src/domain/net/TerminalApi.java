package domain.net;


import domain.model.User;
import io.reactivex.Observable;

public interface TerminalApi {

    Observable<User> createAccount(final String name, final String username, final String email, final String password);

}

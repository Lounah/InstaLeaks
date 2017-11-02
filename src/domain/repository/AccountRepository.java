package domain.repository;


import domain.model.User;
import io.reactivex.Observable;

public interface AccountRepository {

    Observable<User> createAccount(final String name, final String username, final String email, final String password);

}

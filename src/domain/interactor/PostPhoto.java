package domain.interactor;

import domain.model.Account;
import domain.model.Response;
import domain.repository.AccountRepository;
import io.reactivex.Observable;

public class PostPhoto extends UseCase<Response, PostPhoto.Params> {


    private final AccountRepository repository;

    public PostPhoto(final AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    Observable<Response> buildUseCaseObservable(PostPhoto.Params params) {
        return repository.postPhotoToAccount(params.user, params.photoPath);
    }

    public static final class Params {

        private Account user;
        private String photoPath;

        private Params(final Account user, final String photoPath) {
            this.user = user;
            this.photoPath = photoPath;
        }

        public static Params forPosting(final Account user, final String photoPath) {
            return new Params(user, photoPath);
        }
    }

}

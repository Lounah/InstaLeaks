package utils;

import domain.model.Account;
import utils.io.FileReader;

import java.util.ArrayList;
import java.util.List;

public final class UserProvider {

    private UserProvider() {}

    public static List<Account> provideUserListFromFile(final String path) {
        List<Account> accounts = new ArrayList<>();
        FileReader fileReader = new FileReader();
        fileReader.read(path).subscribe(accounts::add);
        return accounts;
    }

}

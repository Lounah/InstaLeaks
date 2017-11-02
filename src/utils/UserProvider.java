package utils;

import domain.model.User;
import utils.file.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class UserProvider {

    private UserProvider() {}

    public static List<User> provideUserList() {
        List<User> users = new ArrayList<>();
        FileReader fileReader = new FileReader();
        fileReader.read(Constants.UNCHECKED_USERS_FILE_PATH).subscribe(users::add);
        return users;
    }

}

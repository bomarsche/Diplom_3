package StellarBurgersAPI;

import org.apache.commons.lang3.RandomStringUtils;


public class UserGenerator {
    public static User getRandomFullData(){
        final String name = RandomStringUtils.randomAlphanumeric(7);
        final String email = name.toLowerCase() + "@yandex.test";
        final String password = RandomStringUtils.randomAlphanumeric(7);
        return new User(email, name, password );
    }

    public static User getRandomWithoutPassword(){
        final String name = RandomStringUtils.randomAlphanumeric(7);
        final String email = name.toLowerCase() + "@yandex.test";
        return new User(email, name, null);
    }

}

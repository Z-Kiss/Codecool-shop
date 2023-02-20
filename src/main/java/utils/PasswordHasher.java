package utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordHasher {

    private Argon2 argon2;

    private static PasswordHasher instance = null;

    public PasswordHasher(int saltLen, int hashLen){
        this.argon2 = Argon2Factory.create(
                Argon2Factory.Argon2Types.ARGON2d,
                saltLen,
                hashLen);
    }

    public static PasswordHasher getInstance(){
        if(instance == null){
            instance = new PasswordHasher(16,32);
        }
        return instance;
    }

    public String hashPassword(String password){
        return argon2.hash(3, // Number of iterations
                64 * 1024, // 64mb
                1, // how many parallel threads to use
                password);

    }

    public boolean verifyPassword(String hashedPassword, String password){
        return argon2.verify(hashedPassword, password);
    }
}
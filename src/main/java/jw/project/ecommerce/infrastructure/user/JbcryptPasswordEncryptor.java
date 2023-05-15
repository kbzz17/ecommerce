package jw.project.ecommerce.infrastructure.user;

import jw.project.ecommerce.domain.user.PasswordEncryptor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class JbcryptPasswordEncryptor implements PasswordEncryptor {
    @Override
    public String encoder(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean CheckPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}

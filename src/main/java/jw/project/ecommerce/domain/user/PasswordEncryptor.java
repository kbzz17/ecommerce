package jw.project.ecommerce.domain.user;

public interface PasswordEncryptor {
    String encoder(String password);
    boolean CheckPassword(String password, String hashed);
}

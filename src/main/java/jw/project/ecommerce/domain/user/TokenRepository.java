package jw.project.ecommerce.domain.user;

public interface TokenRepository {
    void save(String refreshToken);

    Boolean deleteToken(String refreshToken);

    Boolean existsKey(String refreshToken);
}

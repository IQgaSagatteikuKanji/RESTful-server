package ru.Cherkasov.project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.Cherkasov.project.model.User;


public interface UserRepository extends JpaRepository<User,Long> {

    @Query("FROM User WHERE email = ?1")
    User findUserByEmail(String email);

    @Query("from User where nickname = ?1")
    User findUserByNickname(String nickname);

    @Query("from User where secretKey = ?1")
    User findUserBySecretKey(String key);


    @Query("SELECT sum(RUB_wallet) FROM User")
    double calculateRUBSumAcrossWalletsOfCurrency();
    @Query("SELECT sum(TON_wallet) FROM User")
    double calculateTONSumAcrossWalletsOfCurrency();
    @Query("SELECT sum(BTC_wallet) FROM User")
    double calculateBTCSumAcrossWalletsOfCurrency();
}

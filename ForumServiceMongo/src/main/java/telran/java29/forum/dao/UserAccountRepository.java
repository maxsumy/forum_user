package telran.java29.forum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.forum.domain.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

}

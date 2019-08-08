package telran.java29.forum;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import telran.java29.forum.dao.UserAccountRepository;
import telran.java29.forum.domain.UserAccount;

@SpringBootApplication
public class ForumServiceMongoApplication implements CommandLineRunner{
	
	@Autowired
	UserAccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(ForumServiceMongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!accountRepository.existsById("admin")) {
			UserAccount admin = UserAccount.builder()
					.login("admin")
					.password("admin")
					.firstName("Super")
					.lastName("Admin")
					.role("Admin")
					.role("User")
					.role("Moderator")
					.expdate(LocalDateTime.now().plusYears(25))
					.build();
			accountRepository.save(admin);
					
		}
		
	}

}

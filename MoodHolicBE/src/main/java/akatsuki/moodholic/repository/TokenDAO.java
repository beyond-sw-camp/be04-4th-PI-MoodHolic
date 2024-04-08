package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenDAO extends CrudRepository<Token, String> {

    Token findBy(String email);
}

package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDAO extends JpaRepository<Member,Long> {


    Member findByUsername(String username);

}

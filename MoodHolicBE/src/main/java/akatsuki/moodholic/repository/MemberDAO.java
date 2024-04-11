package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface MemberDAO extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);


//    @Query("SELECT m FROM Member m WHERE m.email = :email AND m.providerCode = :providerId")
//    Optional<Member> findByEmailAndProviderId(@Param("email") String email, @Param("providerId") String providerId);

//    Optional<Member> findAllByEmail

    Optional<Member> findByProviderCode(String providerCode);

    Optional<Member> findByEmailAndProvider(String userId, String providerCode);
}

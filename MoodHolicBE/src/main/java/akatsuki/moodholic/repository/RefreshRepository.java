package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.RefreshEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshEntity, Long> {

    Boolean existsByRefresh(String refresh);

    @Override
    Optional<RefreshEntity> findById(Long aLong);

    //    @Transactional
//    void deleteByRefresh(String refresh);

    @Transactional
    void deleteByRefreshToken(String refresh);

    Optional<RefreshEntity> findByUsername(String email);

    void deleteByKey(String refresh);
}

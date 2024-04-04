package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryDAO extends JpaRepository<Diary, Integer> {

}

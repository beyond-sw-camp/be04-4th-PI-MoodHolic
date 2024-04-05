package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryDAO extends JpaRepository<Diary, Integer> {
    
    List<Diary> findAllByMemberMemberId(long memberId);

//    List<Diary> findAllByMemberMemberIdByDate(long memberId);
}

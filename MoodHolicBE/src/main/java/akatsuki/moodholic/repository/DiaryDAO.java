package akatsuki.moodholic.repository;

import akatsuki.moodholic.domain.Diary;
import akatsuki.moodholic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryDAO extends JpaRepository<Diary, Integer> {
    
    List<Diary> findAllByMemberMemberId(long memberId);

    List<Diary> findAllByMemberMemberIdOrderByDateAsc(long memberId);


    Diary findByMemberMemberIdAndDate(long memberId, String date);

    Long countByMemberMemberId(long memberId);
}

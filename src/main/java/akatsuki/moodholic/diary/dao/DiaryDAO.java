package akatsuki.moodholic.diary.dao;

import akatsuki.moodholic.diary.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryDAO extends JpaRepository<DiaryEntity, Integer> {

}

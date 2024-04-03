package akatsuki.moodholic.member.dao;

import akatsuki.moodholic.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDAO extends JpaRepository<MemberEntity,Long> {

}

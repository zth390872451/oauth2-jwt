package babywei.authserver.service;

import babywei.authserver.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @creator ZTH
 * @modifier ZTH
 * @date 2017-09-26
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
	public Member findOneByMobile(String mobile);
}

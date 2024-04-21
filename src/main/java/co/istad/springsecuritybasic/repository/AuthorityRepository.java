package co.istad.springsecuritybasic.repository;

import co.istad.springsecuritybasic.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,String> {


}

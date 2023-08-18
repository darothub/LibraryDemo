package uk.gov.homeoffice.springtechtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.homeoffice.springtechtest.models.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

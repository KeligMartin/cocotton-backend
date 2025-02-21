package fr.esgi.cocotton.infrastructure.session.persistence;

import fr.esgi.cocotton.infrastructure.profile.persistance.JpaProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaSessionRepository extends JpaRepository<JpaSession, String> {

    List<JpaSession> findAll();

    List<JpaSession> findAllByjpaProfile(JpaProfile jpaProfile);

    Optional<JpaSession> findByToken(String token);

    Optional<JpaSession> findById(String id);
}

package it.peppemig.link4bio.repository;

import it.peppemig.link4bio.entity.Click;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickRepository extends JpaRepository<Click, Long> {
}

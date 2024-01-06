package it.peppemig.link4bio.repository;

import it.peppemig.link4bio.entity.Click;
import it.peppemig.link4bio.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ClickRepository extends JpaRepository<Click, Long> {
    Optional<Click> findByLinkAndClickDate(Link link, Date clickDate);
}

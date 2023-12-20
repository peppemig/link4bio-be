package it.peppemig.link4bio.repository;

import it.peppemig.link4bio.entity.Button;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ButtonRepository extends JpaRepository<Button, Long> {
}

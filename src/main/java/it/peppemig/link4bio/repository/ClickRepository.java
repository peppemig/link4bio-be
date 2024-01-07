package it.peppemig.link4bio.repository;

import it.peppemig.link4bio.dto.LinkAnalyticsDTO;
import it.peppemig.link4bio.entity.Click;
import it.peppemig.link4bio.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClickRepository extends JpaRepository<Click, Long> {
    Optional<Click> findByLinkAndClickDate(Link link, Date clickDate);

    @Query("SELECT NEW it.peppemig.link4bio.dto.LinkAnalyticsDTO(c.link.id, c.link.title, c.link.subtitle, c.link.url, " +
            "SUM(c.clickCount), SUM(CASE WHEN c.clickDate = :currentDate THEN c.clickCount ELSE 0 END)) " +
            "FROM Click c " +
            "WHERE c.link.page.user.id = :userId " +
            "GROUP BY c.link.id, c.link.title, c.link.subtitle, c.link.url")
    List<LinkAnalyticsDTO> getTotalClicksPerLinkByUserId(@Param("userId") String userId, @Param("currentDate") Date currentDate);
}

package it.peppemig.link4bio.service;

import it.peppemig.link4bio.dto.LinkAnalyticsDTO;
import it.peppemig.link4bio.repository.ClickRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AnalyticsService {
    private final ClickRepository clickRepository;

    public AnalyticsService(ClickRepository clickRepository) {
        this.clickRepository = clickRepository;
    }

    public List<LinkAnalyticsDTO> getTotalClicksPerLinkByUserId(String userId) {
        Date currentDate = truncateTime(new Date());
        return clickRepository.getTotalClicksPerLinkByUserId(userId, currentDate);
    }

    private Date truncateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}

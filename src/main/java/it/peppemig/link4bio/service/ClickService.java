package it.peppemig.link4bio.service;

import it.peppemig.link4bio.dto.ClickDTO;
import it.peppemig.link4bio.entity.Click;
import it.peppemig.link4bio.entity.Link;
import it.peppemig.link4bio.exceptions.UnauthorizedException;
import it.peppemig.link4bio.repository.ClickRepository;
import it.peppemig.link4bio.repository.LinkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class ClickService {
    private final ClickRepository clickRepository;
    private final LinkRepository linkRepository;
    private final ModelMapper modelMapper;

    public ClickService(ClickRepository clickRepository, LinkRepository linkRepository, ModelMapper modelMapper) {
        this.clickRepository = clickRepository;
        this.linkRepository = linkRepository;
        this.modelMapper = modelMapper;
    }

    public ClickDTO saveClickToLink(Long linkId) {
        Link link = linkRepository.findById(linkId).orElseThrow(() -> new EntityNotFoundException("Link not found with ID: " + linkId));
        Date currentDate = truncateTime(new Date());
        Optional<Click> existingClick = clickRepository.findByLinkAndClickDate(link, currentDate);
        if (existingClick.isPresent()) {
            existingClick.get().setClickCount(existingClick.get().getClickCount() + 1);
            Click updatedClick = clickRepository.save(existingClick.get());
            return modelMapper.map(updatedClick, ClickDTO.class);
        } else {
            Click newClick = new Click();
            newClick.setLink(link);
            newClick.setClickDate(currentDate);
            Click savedClick = clickRepository.save(newClick);
            return modelMapper.map(savedClick, ClickDTO.class);
        }
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

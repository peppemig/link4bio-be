package it.peppemig.link4bio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkAnalyticsDTO {
    private Long linkId;
    private String linkTitle;
    private String linkSubtitle;
    private String linkUrl;
    private Long totalClickCount;
    private Long totalClickCountToday;
}

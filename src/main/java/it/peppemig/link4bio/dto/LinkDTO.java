package it.peppemig.link4bio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {
    private Long id;
    private String title;
    private String subtitle;
    private String imageUrl;
    private String url;
}

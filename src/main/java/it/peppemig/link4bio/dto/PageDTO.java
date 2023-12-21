package it.peppemig.link4bio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    private Long id;
    private String uri;
    private UserDTO user;
    private List<LinkDTO> links;
    private List<ButtonDTO> buttons;
}

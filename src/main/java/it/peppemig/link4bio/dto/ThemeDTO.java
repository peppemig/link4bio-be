package it.peppemig.link4bio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDTO {
    private Long id;
    private String backgroundColor;
    private String textColor;
    private String buttonColor;
    private String buttonTextColor;
}

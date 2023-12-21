package it.peppemig.link4bio.dto;

import it.peppemig.link4bio.enums.ButtonName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButtonDTO {
    private Long id;
    private ButtonName name;
    private String url;
    private Integer clickCount;
}

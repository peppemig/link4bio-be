package it.peppemig.link4bio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClickDTO {
    private Long id;
    private Date clickDate;
    private int clickCount;
}

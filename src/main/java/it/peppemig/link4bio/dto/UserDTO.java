package it.peppemig.link4bio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String email;
    private String displayName;
    private String avatarUrl;
    private String location;
    private String bio;
}

package it.peppemig.link4bio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(unique = true, nullable = false)
    private String id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String displayName;
    private String avatarUrl;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String bio;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Page page;
}

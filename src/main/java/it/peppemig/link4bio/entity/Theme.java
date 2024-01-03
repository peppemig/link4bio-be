package it.peppemig.link4bio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "themes")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "theme_generator", sequenceName = "theme_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String backgroundColor;
    @Column(nullable = false)
    private String textColor;
    @Column(nullable = false)
    private String buttonColor;
    @Column(nullable = false)
    private String buttonTextColor;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "theme")
    private Page page;
}

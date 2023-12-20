package it.peppemig.link4bio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "link_generator", sequenceName = "link_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String subtitle;
    private String imageUrl;
    @Column(nullable = false)
    private String url;
    private Integer clickCount = 0;
    @ManyToOne
    @JoinColumn(name = "page_id")
    @JsonIgnore
    private Page page;
}

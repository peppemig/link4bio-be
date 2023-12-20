package it.peppemig.link4bio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.peppemig.link4bio.enums.ButtonName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "buttons")
public class Button {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "button_generator", sequenceName = "button_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ButtonName name;
    @Column(nullable = false)
    private String url;
    private Integer clickCount = 0;
    @ManyToOne
    @JoinColumn(name = "page_id")
    @JsonIgnore
    private Page page;
}

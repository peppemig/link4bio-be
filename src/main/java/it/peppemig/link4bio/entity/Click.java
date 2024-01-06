package it.peppemig.link4bio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clicks")
public class Click {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "click_generator", sequenceName = "click_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "link_id")
    private Link link;
    @Temporal(TemporalType.DATE)
    private Date clickDate;
    private int clickCount = 1;
}

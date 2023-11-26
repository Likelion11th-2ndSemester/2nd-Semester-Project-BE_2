package com.swuProject.secound.domain.Studio;

import com.swuProject.secound.domain.Studio.Studio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long id;

    private String review;

    // 다대일 단방향 - 사진
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="studio_id")
    private Studio studio;
}

package com.nisum.user.api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phone {

    @Id
    private String number;

    private String cityCode;

    private String countryCode;

    @ManyToOne()
    @JoinColumn(name = "user_uuid",
            referencedColumnName = "uuid")
    @ToString.Exclude
    @JsonBackReference
    private User user;
}

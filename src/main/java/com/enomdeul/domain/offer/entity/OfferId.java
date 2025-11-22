package com.enomdeul.domain.offer.entity;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OfferId implements Serializable {
    private Long offerer;
    private Long offeree;
}
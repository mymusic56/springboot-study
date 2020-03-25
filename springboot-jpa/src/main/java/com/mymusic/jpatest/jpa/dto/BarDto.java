package com.mymusic.jpatest.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BarDto {
    Long id;
    String bar;
}

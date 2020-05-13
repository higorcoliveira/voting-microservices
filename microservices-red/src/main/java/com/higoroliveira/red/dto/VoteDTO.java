package com.higoroliveira.red.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteDTO {
    Integer count;
    String district;
    String unavailableMessage;
}

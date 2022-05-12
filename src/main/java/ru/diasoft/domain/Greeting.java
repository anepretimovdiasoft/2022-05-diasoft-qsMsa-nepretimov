package ru.diasoft.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Greeting {

    private final long id;

    private final String content;

}

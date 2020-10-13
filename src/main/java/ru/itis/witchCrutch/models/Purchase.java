package ru.itis.witchCrutch.models;

import lombok.*;

@Data
@ToString
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class Purchase {
    private int id;
    private int basketId;
    private String date;
}

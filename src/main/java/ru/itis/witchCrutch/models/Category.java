package ru.itis.witchCrutch.models;

import lombok.*;

@Data
@ToString
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class Category {
    private String name;
    private String image;
}

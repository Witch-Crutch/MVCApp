package ru.itis.witchCrutch.models;

import lombok.*;

import java.sql.Timestamp;

@Data
@ToString
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class Message {
    User sender;
    User receiver;
    String message;
    @Builder.Default
    String filename = ".";
    @Builder.Default
    Timestamp date = new Timestamp(System.currentTimeMillis());
}

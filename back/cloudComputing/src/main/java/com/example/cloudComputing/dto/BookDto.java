package com.example.cloudComputing.dto;

import lombok.*;

import java.util.Date;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private String author;
    private int pages;

}

package uz.pdp.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Book {

    private Integer id;
    private String title;
    private int pages;

}

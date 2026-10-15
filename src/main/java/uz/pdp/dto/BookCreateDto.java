package uz.pdp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateDto {

    @NotBlank( message = "Title cannot be empty")
    private String title;

    @NotNull( message = "Pages cannot be empty")
    @Min(value = 1, message = "Pages must be at least 1")
    private Integer pages;
}

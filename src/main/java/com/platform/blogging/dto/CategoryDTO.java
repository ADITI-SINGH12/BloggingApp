package com.platform.blogging.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    @NotNull
    private int id;
    @NotNull
    @Size(min=4)
    private String title;
    @NotNull
    @Size(min=5)
    private String description;
}

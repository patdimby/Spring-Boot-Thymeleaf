package com.patdimby.simplerest.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    @NotEmpty(message = "Post title should not be empty")
    private String title;

    private String url;

    @NotEmpty(message = "Post content should not be empty")
    private String content;

    @NotEmpty(message = "Post short description should be empty")

    private String shortDescription;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private int img;

    private String author;
}

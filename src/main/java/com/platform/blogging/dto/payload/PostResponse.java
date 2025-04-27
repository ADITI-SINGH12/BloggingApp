package com.platform.blogging.dto.payload;

import com.platform.blogging.dto.PostDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalElemets;
    private int totalPages;
    private boolean isLastPage;
}

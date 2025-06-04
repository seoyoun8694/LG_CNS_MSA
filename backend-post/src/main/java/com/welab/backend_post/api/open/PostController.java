package com.welab.backend_post.api.open;

import com.welab.backend_post.common.dto.ApiResponseDto;
import com.welab.backend_post.domain.dto.PostCommentCreateDto;
import com.welab.backend_post.domain.dto.PostCreateDto;
import com.welab.backend_post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/post/v1", produces = MediaType.APPLICATION_JSON_VALUE)@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping(value = "/post")
    public ApiResponseDto<String> createPost(@RequestBody @Valid PostCreateDto dto) {postService.createPost(dto);
        return ApiResponseDto.defaultOk();
    }
    @PostMapping(value = "/post/comment")
    public ApiResponseDto<String> addComment(@RequestBody @Valid PostCommentCreateDto dto) {postService.addPostComment(dto);
        return ApiResponseDto.defaultOk();
    }
}
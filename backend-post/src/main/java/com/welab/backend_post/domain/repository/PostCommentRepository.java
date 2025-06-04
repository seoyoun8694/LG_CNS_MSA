package com.welab.backend_post.domain.repository;

import com.welab.backend_post.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {PostComment findByUserId(String userId);
}
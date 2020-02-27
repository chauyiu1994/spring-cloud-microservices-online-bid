package com.chauyiu1994.onlineBidProductsService.services.patchUtils;

import com.chauyiu1994.onlineBidProductsService.domains.Comment;
import org.springframework.stereotype.Component;

// patch only allow on content
@Component
public class CommentPathUtil {

    public boolean patchComment(Comment targetComment, Comment comment) {

        return updateContent(targetComment, comment);
    }

    private boolean updateContent(Comment targetComment, Comment comment) {

        if (comment.getContent() != null && !comment.getContent().equals(targetComment.getContent())) {
            targetComment.setContent(comment.getContent());
            return true;
        }
        return false;
    }
}

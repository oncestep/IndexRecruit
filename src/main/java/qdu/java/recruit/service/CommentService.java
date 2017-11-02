package qdu.java.recruit.service;

import com.github.pagehelper.PageInfo;
import qdu.java.recruit.pojo.UserCommentBO;

public interface CommentService {

    PageInfo<UserCommentBO> listComment(int positionId, int page, int limit);



}

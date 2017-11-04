package qdu.java.recruit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import qdu.java.recruit.mapper.CommentMapper;
import qdu.java.recruit.pojo.CommentPositionBO;
import qdu.java.recruit.pojo.UserCommentBO;
import qdu.java.recruit.service.CommentService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public PageInfo<UserCommentBO> listComment(int positionId,int page,int limit){

        PageHelper.startPage(page,limit);
        List<UserCommentBO> commentList = commentMapper.listUserComment(positionId);
        PageInfo<UserCommentBO> comList = new PageInfo<>(commentList);
        return comList;
    }

    @Override
    public boolean commentPosition(int type,String content,int userId,int positionId){

        //获取当前日期时间
        java.util.Date date = new java.util.Date();
        Timestamp releaseTime = new Timestamp(date.getTime());

        int result = commentMapper.saveComment(type,content,releaseTime,userId,positionId);
        if(result > 0){
            return true;
        }
        return false;
    }


}

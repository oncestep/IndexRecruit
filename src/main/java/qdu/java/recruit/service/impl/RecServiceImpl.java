package qdu.java.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.mapper.*;
import qdu.java.recruit.service.RecService;

import java.util.ArrayList;


@Service
public class RecServiceImpl implements RecService {

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CollectionMapper collectionMapper;

    //Resume
    @Override
    public int findResume(int userId) {
        int resumeId = 0;
        try {
            resumeId = resumeMapper.queryResume(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resumeId;
    }

    //Position
    @Override
    public ArrayList<Position> findPosAll() {
        ArrayList<Position> posListAll = null;
        try {
            posListAll = positionMapper.queryPosAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posListAll;
    }

    @Override
    public Position findPosition(int posId) {
        Position pos = null;
        try {
            pos = positionMapper.queryPosition(posId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pos;
    }

    @Override
    public ArrayList<Position> findPosHR(int hrId){
        ArrayList<Position> posList = new ArrayList<Position>();
        try{
            posList = positionMapper.queryPosHR(hrId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return posList;

    }

    //User
    public ArrayList<User> findUserAll() {
        ArrayList<User> userListAll = null;
        try {
            userListAll = userMapper.queryUserAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userListAll;
    }

    public User findUser(int userId) {
        User user = null;
        try {
            user = userMapper.queryUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    //Application
    @Override
    public ArrayList<Application> findApplication(int resumeId) {
        ArrayList<Application> posList = null;
        try {
            posList = applicationMapper.queryApplication(resumeId);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return posList;
    }

    @Override
    public boolean matchApplication(int resumeId,int posId){
        Application app = null;
        try{
            app =applicationMapper.queryMatch(resumeId,posId);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(app == null)
            return false;
        else
            return true;
    }

    //Comment
    @Override
    public ArrayList<Comment> findComment(int userId) {
        ArrayList<Comment> comList = null;
        try {
            comList = commentMapper.queryComment(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comList;
    }

    @Override
    public Comment matchComment(int userId,int posId){
        Comment com = null;
        try{
            com = commentMapper.queryMatch(userId,posId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return com;
    }

    //Collection
    @Override
    public ArrayList<Collection> findCollection(int userId) {
        ArrayList<Collection> colList = null;
        try {
            colList = collectionMapper.queryCollection(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colList;
    }

    @Override
    public boolean matchCollection(int userId, int posId) {
        Collection col = null;
        try {
            col = collectionMapper.queryMatch(userId, posId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (col == null)
            return false;
        else
            return true;
    }

    @Override
    public ArrayList<Integer> findElse(int userId, int hostId) {
        ArrayList<Integer> colList = null;
        try {
            colList = collectionMapper.queryElse(userId, hostId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colList;
    }

}

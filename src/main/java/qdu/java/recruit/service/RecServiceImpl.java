package qdu.java.recruit.service;

import qdu.java.recruit.domain.*;

import java.util.ArrayList;

public interface RecServiceImpl {

    //Resume
    int findResume(int userId);



    //Position
    ArrayList<Position> findPosAll();

    Position findPosition(int posId);

    ArrayList<Position> findPosHR(int hrId);


    //User
    ArrayList<User> findUserAll();

    User findUser(int userId);



    //Application
    ArrayList<Application> findApplication(int resumeId);

    boolean matchApplication(int resumeId,int posId);



    //Comment
    ArrayList<Comment> findComment(int userId);

    Comment matchComment(int userId,int posId);



    //Collection
    ArrayList<Collection> findCollection(int userId);

    boolean matchCollection(int userId, int posId);

    ArrayList<Integer> findElse(int userId, int hostId);
}

package whustore.dao;

import whustore.model.User;

import java.util.List;

interface UserDaoIntf {

    User loginCheck(User user);

    boolean userReg(User user);

    boolean userModify(User user);

    boolean checkTeamid(int iduser, int idteam);

    List<User> getAllUser();

    User getUserByIduser(int iduser);

    User getUserByUsername(String username);

    <T> User findBy(T key, String sql);

    boolean deleteUserByIduser(int iduser);

    <T> boolean deleteUserBy(T key, String sql);


}

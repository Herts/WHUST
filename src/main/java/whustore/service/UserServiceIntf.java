package whustore.service;

import whustore.model.User;

import java.util.List;

interface UserServiceIntf {
    User loginCheck(User user);

    boolean userReg(User user);

    boolean checkTeamid (int iduser, int idteam);

    List<User> getAllUser();

    User getUserByUsername(String username);

    boolean isUsernameUnique(String username);

    boolean deleteUserByIduser(int iduser);

    void updateUser(User user);

}

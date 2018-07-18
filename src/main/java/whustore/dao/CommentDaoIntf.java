package whustore.dao;

import whustore.model.Comment;

import java.util.List;

public interface CommentDaoIntf {
    List<Comment> getCommentByIduser(int iduser);

    List<Comment> getCommentByIdproduct(int idproduct);

    Comment getCommentByIduserAndIdproduct(int iduser, int idproduct);


    boolean setComment(int idproduct, int iduser, int clevel, String ctitle, String ccontent, Boolean isCommented);

    boolean insertComment(int idproduct, int iduser, int clevel, String ctitle, String ccontent);

    boolean updateExe(int idproduct, int iduser, int clevel, String ctitle, String ccontent, String sql);

    boolean updateComment(int idproduct, int iduser, int clevel, String ctitle, String ccontent);
}

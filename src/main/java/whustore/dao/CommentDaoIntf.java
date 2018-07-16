package whustore.dao;

import whustore.model.Comment;

import java.util.List;

public interface CommentDaoIntf {
    public List<Comment> getCommentByIduser(int iduser);
    public List<Comment> getCommentByIdproduct(int idproduct);
    public Comment getCommentByIduserAndIdproduct(int iduser, int idproduct);


    public boolean setComment(int idproduct,int iduser,int clevel ,String ctitle, String ccontent,Boolean isCommented);

    public boolean insertComment(int idproduct,int iduser,int clevel ,String ctitle, String ccontent);
    public boolean updateExe(int idproduct, int iduser, int clevel , String ctitle, String ccontent, String sql);
    public boolean updateComment(int idproduct, int iduser, int clevel, String ctitle, String ccontent);
}

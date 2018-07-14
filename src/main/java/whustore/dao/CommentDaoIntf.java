package whustore.dao;

import whustore.model.Comment;

import java.util.List;

public interface CommentDaoIntf {
    public List<Comment> getCommentByIduser(int iduser);
    public List<Comment> getCommentByIdproduct(int idproduct);
    public boolean setComment(int idproduct,int iduser,int clevel ,String ctitle, String ccomment);

}

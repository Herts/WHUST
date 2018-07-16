package whustore.service;

import org.springframework.stereotype.Service;
import whustore.dao.CommentDao;
import whustore.model.Comment;

import java.util.List;
@Service
public class CommentService {
    CommentDao commentDao = new CommentDao();

    public Comment getCommentByIduserAndIdproduct(int iduser, int idproduct){
        return commentDao.getCommentByIduserAndIdproduct(iduser, idproduct);
    }


    public List<Comment> getCommentByIduser(int iduser){
        return commentDao.getCommentByIduser(iduser);

    }

    public List<Comment> getCommentByIdproduct(int idproduct){
        return commentDao.getCommentByIdproduct(idproduct);
    }

    public boolean setComment(int idproduct, int iduser, int clevel , String ctitle, String ccomment, boolean isCommented){
        return commentDao.setComment(idproduct, iduser, clevel , ctitle, ccomment, isCommented);
    }
}

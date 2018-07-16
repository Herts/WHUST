package whustore.model;

import java.sql.Timestamp;

public class Comment {
    private int iduser;
    private int idproduct;
    private int clevel;
    private String ctitle;
    private String ccontent;
    private boolean isBought;
    private Timestamp commentsince;

    public Timestamp getCommentsince() {
        return commentsince;
    }

    public void setCommentsince(Timestamp commentsince) {
        this.commentsince = commentsince;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public int getClevel() {
        return clevel;
    }

    public void setClevel(int clevel) {
        this.clevel = clevel;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }
}

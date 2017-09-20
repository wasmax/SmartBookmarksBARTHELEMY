package com.example.barthelemy.smartbookmarks.Models;

import java.util.Date;

/**
 * Created by Max on 20/09/2017.
 */

public class Comment {

    Integer id;
    String comment;
    Livre livre;
    Date date;

    public Comment(Integer id, String comment, Livre livre, Date date) {
        this.id = id;
        this.comment = comment;
        this.livre = livre;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
}

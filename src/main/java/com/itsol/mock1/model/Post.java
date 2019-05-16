package com.itsol.mock1.model;

import org.ocpsoft.pretty.time.PrettyTime;
import org.springframework.security.access.method.P;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="post")
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private  int id;
    @Column(name = "contend", nullable = false)
    private  String contend;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column (name = "createby")
    private String createby;
    @Column(name = "createdate")
    private String date;
    @Column(name = "sumary")
    private String sumary;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Post(){
        this.thumbnail = null;
    }
    public Post(String contend){
        this.contend = contend;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContend() {
        return contend;
    }

    public void setContend(String contend) {
        this.contend = contend;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getPrettyDate(String str) throws ParseException {
        PrettyTime prettyTime = new PrettyTime();
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(str);
        } catch (ParseException e){
            e.getMessage();
        }
        return date!=null?prettyTime.format(date):"";
    }
}

package com.example.jeevan.testapp.local_models;

/**
 * Created by jeevan on 5/21/17.
 */

public class Test2Item {
    private int id;
    private String title, content;
    private boolean starred;

    public Test2Item() {

    }

    public Test2Item(Test2Item a) {
        this.id = a.getId();
        this.title = a.getTitle();
        this.content = a.getContent();
        this.starred = a.isStarred();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public String toString() {
        return id + ": " + title + ", " + content + ", " + starred;
    }
}

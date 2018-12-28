package com.example.thomas.gymclubapp.Models;

public class News {
    private String Title;
    private String Content;

    public News(String title, String content) {
        this.Title = title;
        this.Content = content;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}

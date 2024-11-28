package com.example.luxevista;

public class Notification {
    private String Title;
    private String Description;
    private String SendTime;

    public Notification() {}

    public Notification(String title, String description, String sendTime) {
        this.Title = title;
        this.Description = description;
        this.SendTime = sendTime;
    }


    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getSendTime() {
        return SendTime;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setSendTime(String sendTime) {
        SendTime = sendTime;
    }
}

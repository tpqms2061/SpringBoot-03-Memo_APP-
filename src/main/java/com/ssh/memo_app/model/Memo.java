package com.ssh.memo_app.model;
//model 은 Service에서 독립적으로 클래스같은 것을 구분한것


import javax.print.DocFlavor;

public class Memo {
    private int id;
    private String title;
    private String content;

    public Memo() {}  //다른 JDBC나 라이브러리가 Memo가 살아있는지 확인하기 위해 설정한 것

    public Memo(int id, String title, String content) {
        this.id =id;
        this.title =title;
        this.content =content;
    }

    public int getId() { return id;}

    public String getTitle() { return title; }
    public String getContent() { return content; }

    public void setId(int id) { this.id = id; }

    public void setTitle(String title) { this.title = title; }

    public void setContent(String content) { this.content = content; }

}

package org.example;

public class MyTopicsubscriber implements Observer{
    private String name;
    private Subject topic;

    public MyTopicsubscriber(String nm){
        this.name=nm;
    }

    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(name+":: No new message");
        }else
        System.out.println(name+":: Consuming message::"+msg);
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic=sub;
    }
}

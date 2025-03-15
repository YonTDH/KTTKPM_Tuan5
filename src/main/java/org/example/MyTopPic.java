package org.example;

import java.util.ArrayList;
import java.util.List;

public class MyTopPic implements Subject{
    private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX= new Object();

    public MyTopPic(){
        this.observers=new ArrayList<>();
    }


    @Override
    public void register(Observer o) {
        if(o == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX){
            if(!observers.contains(o)) observers.add(o);
        }
    }

    @Override
    public void unregister(Observer o) {
        synchronized (MUTEX){
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;
        synchronized (MUTEX){
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed=false;
        }
        for (Observer obj : observersLocal) {
            obj.update();
        }
    }

    @Override
    public Object getUpdate(Observer o) {
        return this.message;
    }

    public void postMessage(String msg){
        System.out.println("Message Posted to Topic:"+msg);
        this.message=msg;
        this.changed=true;
        notifyObservers();
    }
}

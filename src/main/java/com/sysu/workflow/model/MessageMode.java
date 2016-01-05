package com.sysu.workflow.model;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/5
 * Time: 19:31
 * User: zhengshouzi
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: a601097836@gmail.com
 */
public enum MessageMode{

    BROADCAST("全局广播",1), TO_OFFSPRING("子孙广播",2),TO_CHILD("孩子广播",3),TO_SIBLING("兄弟广播",4),TO_ANCESTOR("祖先广播",5),MULTICAST("一组多播",6),TO_PARENT("父亲单播",7),UNICAST("任意单播",8);

    private String name;
    private int index;


    MessageMode(String name,int index){
        this.name=name;
        this.index=index;
    }

}


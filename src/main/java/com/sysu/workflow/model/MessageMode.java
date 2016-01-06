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

    BROADCAST("Broadcast"), TO_OFFSPRING("To_Offspring"),TO_CHILD("To_Child"),TO_SIBLING("To_Sibling"),TO_ANCESTOR("To_Ancestor"),MULTICAST("Multicast"),TO_PARENT("To_Parent"),UNICAST("Unicast");

    private String name;



    MessageMode(String name){
        this.name=name;
    }

}


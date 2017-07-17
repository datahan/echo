package cn.enn.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import redis.clients.jedis.Jedis;

@ServerEndpoint("/test")
public class EchoEndPoint {
    
    // 设置redis实时库的ip地址和端口
    static Jedis jedis = new Jedis("10.20.2.26", 6379);
    
    public EchoEndPoint() {
        System.out.println("EchoEndPoint构造初始化...");
    }
    
    @OnOpen
    public void open(Session session) {
        
        System.out.println("sessonid: " + session.getId());
    }
    
    @OnClose
    public void close(Session session) {
        System.out.println("sessonid: " + session.getId() + "关闭了...");
    }
    
    @OnMessage
    public void message(Session session, String msg) throws Exception {
        
        System.out.println("客户端： " + msg);
        String str = new String();
        
        List<String> items = new ReadCode().readAllCode();
        while(true) {
            Iterator<String> iterator = items.iterator();
            while(iterator.hasNext()) {
                String tag = iterator.next();
                String value = jedis.get(tag);
                str += "\"" + tag + "\"" + ":" + value + ",";
            }
            
            str = str.substring(0, str.length()-1);
            
            session.getBasicRemote().sendText("{" + str + "}");
            str = "";
            Thread.sleep(1000);
        }
    }
    
    @OnError
    public void onError(Throwable t) throws Throwable {
        System.out.println("Chat Error: " + t.toString());
    }
}

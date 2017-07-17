package cn.enn.server;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class DemoConfig implements ServerApplicationConfig {

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        System.out.println("config..." + scanned.size());
        return scanned;
    }

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    
}

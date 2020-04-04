package com.rabbion.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;


public class FiveRule extends AbstractLoadBalancerRule {

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    public Server choose(ILoadBalancer lb, Object key) {
        int count = 0;//每台服务器访问次数
        int serverIndex = 0;//每台服务器下标

        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {//当所有服务器死光才停止
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();//活着的server
            List<Server> allList = lb.getAllServers();//所有server

            int serverCount = allList.size();
            if (serverCount == 0) {
                return null;
            }

            if(count<5) {
                server = upList.get(serverIndex);//访问当前服务器
                count ++;//访问次数增加
            }else {
                count = 0;
                serverIndex++;//五次后切换另一台服务器
                if(serverIndex>upList.size()) {
                    serverIndex = 0;//所有可用服务器循环完毕后再次循环
                }
            }

            if (server == null) {

                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return server;
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }
}

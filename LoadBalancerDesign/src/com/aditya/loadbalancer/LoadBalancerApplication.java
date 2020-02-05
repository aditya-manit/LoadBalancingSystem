package com.aditya.loadbalancer;

//FEATURES:
//1)Dispatch the request to the server having the lowest number of requests.
//2)It should maintain a list of active servers only and should dispatch a request to a active server only.
//3)If a server goes down, it should redistribute all of its requests evenly among all other servers which are active.
//4)If a server goes up --> you need to again reBalance it all. --> YET TO IMPLEMENT !!

import java.util.PriorityQueue;

public class LoadBalancerApplication {
    public static void main(String[] args) {

        LoadBalancerSystem loadBalancerSystem = new LoadBalancerSystem();
        HealthChecker healthChecker = new HealthChecker();


        // 3 SERVERS
        Server server1 = new Server(101);
        Server server2 = new Server(102);
        Server server3 = new Server(103);

        // INITIALIZING THE SYSTEM
        addServer(server1, loadBalancerSystem, healthChecker);
        addServer(server2, loadBalancerSystem, healthChecker);
        addServer(server3, loadBalancerSystem, healthChecker);



        // 5 REQUESTS
        processRequest(7001,"Want Access to Github",loadBalancerSystem);
        processRequest(7002,"Want Access to Jira",loadBalancerSystem);
        processRequest(7003,"Want Access to Concur",loadBalancerSystem);
        processRequest(7004,"Want Access to VPN",loadBalancerSystem);
        processRequest(7005,"Generate my SSH key",loadBalancerSystem);
        processRequest(7006,"Give me a Team",loadBalancerSystem);
        processRequest(7007,"I want a new mobile",loadBalancerSystem);
        processRequest(7008,"I need peanut butter",loadBalancerSystem);


        healthChecker.doHealthCheck(loadBalancerSystem);
        System.out.println(loadBalancerSystem);


        server1.down();
        healthChecker.doHealthCheck(loadBalancerSystem);


        System.out.println(loadBalancerSystem);
        server1.up();





    }


    
    private static void processRequest(int reqID, String reqStatement, LoadBalancerSystem loadBalancerSystem) {
        loadBalancerSystem.processNewRequest(new Request(reqID, reqStatement));
    }


    private static void addServer(Server server, LoadBalancerSystem loadBalancerSystem, HealthChecker healthChecker) {
        loadBalancerSystem.addNewServer(server);
        healthChecker.addNewServer(server);
    }
}

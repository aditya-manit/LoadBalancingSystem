package com.aditya.loadbalancer;

import java.util.ArrayList;
import static com.aditya.loadbalancer.Status.*;



public class HealthChecker {
    private ArrayList<Server> listOfAllServers = new ArrayList<Server>();

    public void addNewServer(Server server) {
        listOfAllServers.add(server);
    }


    public void doHealthCheck(LoadBalancerSystem loadBalancerSystem)
    {
        System.out.println("Health check is in progress!! Please wait....");
        for( Server server : listOfAllServers)
        {
            if(server.getStatus().equals(DOWN))
            {
                informLoadBalancerSystem(loadBalancerSystem,server);
            }
        }
        try
        {
            Thread.sleep(4000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("Health check completed");

    }

    private void informLoadBalancerSystem(LoadBalancerSystem loadBalancerSystem, Server server) {
        loadBalancerSystem.distributeRequestsOfDownServer(server);
    }
}

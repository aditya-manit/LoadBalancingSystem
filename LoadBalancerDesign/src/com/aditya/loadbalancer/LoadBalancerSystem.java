package com.aditya.loadbalancer;
import javafx.util.Pair;
import java.util.*;


public class LoadBalancerSystem {
    private HashMap<Server, ArrayList<Request>> mapOfActiveServersWithListOfRequests = new HashMap<Server,ArrayList<Request>>();
    //private PriorityQueue<Pair<Integer,Server>> minHeap = new PriorityQueue<>();
    PriorityQueue<Pair<Integer,Server>> minHeap = new
            PriorityQueue<Pair<Integer,Server>>(5, new Comparator<Pair<Integer, Server>>() {
        @Override
        public int compare(Pair<Integer, Server> first, Pair<Integer, Server> second) {
            return first.getKey() - second.getKey();
        }
    });


    public void addNewServer(Server server) {
        mapOfActiveServersWithListOfRequests.put(server,new ArrayList<Request>());
        minHeap.add(new Pair<>(0, server));

    }

    public void processNewRequest(Request request) {
        if(minHeap.size()<=0)
        {
            System.out.println("All Servers are Down !!");
            return;
        }
        while(minHeap.size()>0) {
            Pair<Integer, Server> pair = minHeap.poll();
            Server server = pair.getValue();
            int numberOfRequest = pair.getKey();
            if (mapOfActiveServersWithListOfRequests.containsKey(server)) {
                mapOfActiveServersWithListOfRequests.get(server).add(request);
                numberOfRequest++;
                minHeap.add(new Pair<Integer, Server>(numberOfRequest, server));
                System.out.println(request.getReqID() + "- " + request.getReqStatement() + " is being processed by Server With ID " + server.getServerID());
                return;

            }
        }
        System.out.println("All Servers are Down !!");
    }

    public void distributeRequestsOfDownServer(Server server) {
        System.out.println("Server" + server.getServerID() + " is found down !! Redistributing its request to the active servers !!");
        ArrayList<Request> listOfRequestForDownServer= mapOfActiveServersWithListOfRequests.get(server);
        mapOfActiveServersWithListOfRequests.remove(server);
        for(Request request : listOfRequestForDownServer)
        {
            processNewRequest(request);
        }
    }

    @Override
    public String toString() {
        for(Server server : mapOfActiveServersWithListOfRequests.keySet())
        {
            System.out.println("Server " +server.getServerID()+ " is handling requests" +'\n');
            ArrayList<Request> requestArrayList=  mapOfActiveServersWithListOfRequests.get(server);
            for(Request request : requestArrayList)
            {
                System.out.println(request.getReqID() + " "   +  request.getReqStatement() +   "\n");
            }
            System.out.println("-----------------------------------------------------------------");
        }
        return "";
    }
}

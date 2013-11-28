package edu.ucr.cs.jepsen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastApp {

    private HazelcastInstance client;
    private Map<Long, Long> syncMap;
    private Set<Long> syncSet;
    
    public HazelcastApp(String nodeAddress, Map<String, Long> hosts) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getGroupConfig().setName("dev" /*"partition" + (hosts.get(nodeAddress))*/).setPassword("dev-pass");
        clientConfig.addAddress(nodeAddress);

        client = HazelcastClient.newHazelcastClient(clientConfig);
    }
    
    public void initSet(String setName) {
        syncSet = client.getSet(setName);
    }
    
    public void initMap(String setMap) {
        syncMap = client.getMap(setMap);
    }
    
    public void writeToMap(Long key,Long value) {
        syncMap.put(key, value);
    }
    
    public void writeToSet(Long value) {
        syncSet.add(value);
    }
    
    public Long readFromMap(String key) {
        return syncMap.get(key);
    }
    
    public Set<Long> readKeysFromMap() {
        return syncMap.keySet();
    }
    
    public Set<Long> readFromSet() {
        return syncSet;
    }
    
    public void clearSet() {
        syncSet.clear();
    }
    
    public void clearMap() {
        syncMap.clear();
    }
    
    public void print(String str) {
        System.out.println(str);
    }
    
    public static void main(String[] args) {
        Map<String, Long> nodeMap = new HashMap<String, Long>();
        nodeMap.put("n1", 1l);
        nodeMap.put("n2", 2l);
        nodeMap.put("n3", 3l);
        nodeMap.put("n4", 4l);
        nodeMap.put("n5", 5l);
        
        HazelcastApp hzApp = new HazelcastApp("n3", nodeMap);
        hzApp.initMap("syncMap");
        Iterator<Long> it = hzApp.readKeysFromMap().iterator();
        while (it.hasNext())
            System.out.println(it.next());
    }
}

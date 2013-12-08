package edu.ucr.cs.jepsen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastApp {

    private HazelcastInstance client;
    private Map<String, Map<Long, Long>> maps;
    private Map<String, Set<Long>> sets;
    private Map<String, List<Long>> lists;
    private Map<String, Queue<Long>> queues;
    
    public HazelcastApp(String nodeAddress) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getGroupConfig().setName("dev").setPassword("dev-pass");
        clientConfig.addAddress(nodeAddress);

        maps = new HashMap<String, Map<Long, Long>>();
        sets = new HashMap<String, Set<Long>>();
        lists = new HashMap<String, List<Long>>();
        queues = new HashMap<String, Queue<Long>>();
        
        client = HazelcastClient.newHazelcastClient(clientConfig);
    }
    
    public void initList(String listName) {
        lists.put(listName, client.<Long>getList(listName));
    }
    
    public void initSet(String setName) {
        sets.put(setName, client.<Long>getSet(setName));
    }
    
    public void initMap(String mapName) {
        maps.put(mapName, client.<Long, Long>getMap(mapName));
    }
    
    public void initQueue(String queueName) {
        queues.put(queueName, client.<Long>getQueue(queueName));
    }
    
    public void writeToMap(String map, Long key,Long value) {
        maps.get(map).put(key, value);
    }
    
    public void writeToSet(String set, Long value) {
        sets.get(set).add(value);
    }
    
    public void writeToList(String list, Long value) {
        lists.get(list).add(value);
    }
    
    public void writeToQueue(String queue, Long value) {
        queues.get(queue).add(value);
    }
    
    public Long readFromMap(String map, String key) {
        return maps.get(map).get(key);
    }
    
    public Set<Long> readKeysFromMap(String map) {
        return maps.get(map).keySet();
    }
    
    public Set<Long> readFromSet(String set) {
        return sets.get(set);
    }
    
    public List<Long> readFromList(String list) {
        return lists.get(list);
    }
    
    public Queue<Long> readFromQueue(String queue) {
        return queues.get(queue);
    }
    
    public void clearSet(String set) {
        if (sets.containsKey(set))
            sets.get(set).clear();
        sets.remove(set);
    }
    
    public void clearMap(String map) {
        if (maps.containsKey(map))
            maps.get(map).clear();
        maps.remove(map);
    }
    
    public void clearList(String list) {
        if (lists.containsKey(list))
            lists.get(list).clear();
        lists.remove(list);
    }
    
    public void clearQueue(String queue) {
        if (queues.containsKey(queue))
            queues.get(queue).clear();
        queues.remove(queue);
    }
    
    public static void main(String[] args) {    
        
        HazelcastApp hzApp = new HazelcastApp("n3");
        hzApp.initMap("syncMap");
        Iterator<Long> it = hzApp.readKeysFromMap("syncMap").iterator();
        while (it.hasNext())
            System.out.println(it.next());
    }
}

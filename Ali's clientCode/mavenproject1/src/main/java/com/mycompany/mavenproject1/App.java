package com.mycompany.mavenproject1;

/**
 * Hello world!
 *
 */
import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.CouchbaseConnectionFactory;
import com.couchbase.client.CouchbaseConnectionFactoryBuilder;
import com.couchbase.client.vbucket.CouchbaseNodeOrder;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
        ArrayList<URI> nodes = new ArrayList<URI>();

        // Add one or more nodes of your cluster (exchange the IP with yours)
        nodes.add(URI.create("http://192.168.40.140:8091/pools"));
       // nodes.add(URI.create("http://192.168.40.141:8091/pools"));
        //nodes.add(URI.create("http://192.168.40.142:8091/pools"));
        //nodes.add(URI.create("http://192.168.40.143:8091/pools"));
        //nodes.add(URI.create("http://192.168.40.144:8091/pools"));
        // Try to connect to the client
        CouchbaseClient client = null;
        
        
        
        
        
        
        
        CouchbaseConnectionFactoryBuilder cfb = new CouchbaseConnectionFactoryBuilder();

// Ovveride default values on CouchbaseConnectionFactoryBuilder

// For example - wait up to 10 seconds for an operation to succeed
        //cfb.setTimeoutExceptionThreshold(3);
        //cfb.setObsPollMax(2);

        CouchbaseConnectionFactory cf=null;
        try {
            cf = cfb.buildCouchbaseConnection(nodes, "default", "", "");
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            client = new CouchbaseClient((CouchbaseConnectionFactory) cf);
            
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
      
        //String mode = "delete";
        String mode = "insert";
        //String mode = "read";
        // Set your first document with a key of "hello" and a value of "couchbase!"
        boolean actionResult;
        int successfulActions = 0;
        int unsuccessfulActions = 0;
        if (mode.compareTo("delete") == 0) {
            int j;
            for (j = 1; j <= 1000; j++) {
                try {
                    //client.delete("test"+j);
                    //client.delete("tost"+j);
                    actionResult = client.delete("tost" + j).get();
                    if (actionResult)
                        successfulActions++;
                    else
                        unsuccessfulActions++;
                } catch (InterruptedException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
                 catch (RuntimeException ex) {
                 //   System.out.println("not seccessful set! 3");
                }
                //System.out.println(j);
            }
            System.out.println("successful= "+ successfulActions);
            System.out.println("unSuccessful= " + unsuccessfulActions);
        }
        if (mode.compareTo("insert") == 0) {
            int i;
            int successfulWrite = 0;
            for (i = 1; i <= 1000; i++) 
            {
                try {
                    actionResult = client.set("tost" + i, "happy").get();
                    if (actionResult)
                        successfulActions++;
                    else
                        unsuccessfulActions++;
                } catch (InterruptedException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("not seccessful set! 1");
                } catch (ExecutionException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("not seccessful set! 2");
                } catch (RuntimeException ex) {
                    System.out.println("not seccessful set! 3");
                }
                   
            }
            System.out.println("successful= "+ successfulActions);
            System.out.println("unSuccessful= " + unsuccessfulActions);
            // Return the result and cast it to string
            //client.removeObserver();
            int counter = 0;
            String result;
            for (i = 1; i <= 1000; i++) {
                try{
                    
                result = (String) client.get("tost" + i);
                }
                catch(Exception e){
                    result = (String) client.getFromReplica("tost" + i);
                }
                //String result = (String) client.asyncGet("tost" + i);
                if (result != null) {
                    counter++;
                }
            }

            System.out.println(successfulWrite + "object wrote successfully!");
            System.out.println(counter + " objects read successfully!");
            //System.out.println("successful= "+ successfulActions);
            //System.out.println("unSuccessful= " + unsuccessfulActions);
            // Shutdown the client*/
        }
        if (mode.compareTo("read") == 0)
        {
            int i = 0;
            int counter = 0;
            String result ;
            for (i = 1; i <= 1000; i++) {
                try{
                 result = (String) client.get("tost" + i);
                }
                catch (Exception E)
                {
                     result = null;
                }
                if (result != null) {
                    counter++;
                }
            }

            System.out.println(counter + " objects read successfully!");
        }
        
        client.shutdown();
    }
}

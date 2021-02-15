package com.javatpoint.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebDao {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Services> getAllServices(String url){ 
	List<Services> servList = null;
	 SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss"); 
	 try { 
         File file = new File("Services.dat"); 
         if (!file.exists()) { 
        	
        	 if(url == null || url == "") {
        		 url = "https://www.google.com";
        	 }
        	 Services user = new Services(url, formatter.format(new Date()), formatter.format(new Date())); 
        	 Future f = pollService(user);
        	 try {
 				f.get();
 			} catch (InterruptedException | ExecutionException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
             if(f != null && f.isDone()) {
        	 servList = new ArrayList<Services>(); 
        	 servList.add(user); 
        	 saveServiceList(servList);
             }
         } 
         else{ 
            FileInputStream fis = new FileInputStream(file); 
            ObjectInputStream ois = new ObjectInputStream(fis); 
            Future f = null;
            servList = (List<Services>) ois.readObject(); 
            if(url != null && url != "") {
            	Services user = new Services(url, formatter.format(new Date()), formatter.format(new Date()));
            	servList.add(user);
            }
            for (Services services : servList) {
            	 f = pollService(services);
			}
            try {
				f.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if(f != null && f.isDone()) {
            saveServiceList(servList);
            ois.close(); 
            }
         } 
      } catch (IOException e1) { 
         e1.printStackTrace(); 
      } catch (ClassNotFoundException e1) { 
         e1.printStackTrace(); 
      }   
      return servList; 
   } 
   @SuppressWarnings("rawtypes")
private Future pollService(Services user) {
	   ApiPoller poller = new ApiPoller(user);
       ExecutorService schedular = Executors.newFixedThreadPool(1);
       //specify the time duration
       Future f = schedular.submit(poller);
		return f;
	}
private void saveServiceList(List<Services> servList){ 
      try { 
         File file = new File("Services.dat"); 
         FileOutputStream fos;  
         fos = new FileOutputStream(file); 
         ObjectOutputStream oos = new ObjectOutputStream(fos); 
         oos.writeObject(servList); 
         oos.close(); 
      } catch (FileNotFoundException e) { 
         e.printStackTrace(); 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } 
   }
public void deleteSelectedService(String id, List<Services> allServices) {
	Iterator<Services> it = allServices.iterator();
	while(it.hasNext()) {
		Services s = (Services) it.next();
		String uri = s.getName().replaceAll("/", "");
		if(uri.equalsIgnoreCase(id)) {
			it.remove();
		}
	}
	saveServiceList(allServices);
	
}    

}

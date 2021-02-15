package com.javatpoint.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.OkHttpClient;

public class ApiPoller implements Runnable{
    private String BASE_URL = "https://api.metamug.com";
    private Services s;
    public ApiPoller(Services user) {
    	super();
		this.BASE_URL = user.getName();
		this.s = user;
		
	}

	public ApiPoller() {
		super();
	}

	OkHttpClient client = new OkHttpClient();

    /**
    * Use OKHttp to send GET API poll request.
    */
    public int getCount() throws IOException {
        int count = 0;
       // Request request = new Request.Builder().url(BASE_URL).build();
        try {
            //JSONObject json = new JSONObject(body.string());
            //JSONArray array = (JSONArray) json.get("max_count");
            //JSONObject firstElement = (JSONObject) array.get(0);
            //count = (Integer) firstElement.get("total");
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			// Iterating condition to if response code is not 200 then throw a runtime
			// exception
			// else continue the actual process of getting the JSON data
			if (responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			else {
				// Scanner functionality will read the JSON data from the stream
				count = 100;
				}
				conn.disconnect();
			
        }catch( Exception e) {
        	if(e instanceof MalformedURLException) {
        		System.out.println(BASE_URL+" URL is malformed!");
        	}
        	 e.printStackTrace();
        }
        return count;
    }

    /**
    * This method gets called after every specified duration
    */
    @Override
    public void run() {
        try {
            int count = this.getCount();
            if(count > 0 ) {
            	s.setStatus("OK");
            }else {
            	s.setStatus("FAIL");
            }
            	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss"); 
   	 Services user = new Services("https://www.google.com", formatter.format(new Date()), formatter.format(new Date()));
       ApiPoller poller = new ApiPoller(user);
       ScheduledExecutorService schedular = Executors.newScheduledThreadPool(1);
       //specify the time duration
       schedular.scheduleAtFixedRate(poller, 0,10, TimeUnit.MINUTES);
    }

}



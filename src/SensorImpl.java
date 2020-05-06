import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.io.StringWriter;
import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONObject.*;
import org.json.JSONArray;

public class SensorImpl extends UnicastRemoteObject implements ISensor {

	public SensorImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	//Add Sensor Details Method Implementation
	@Override
	public int addsensor(SensorModel sensormodel) throws RemoteException{
		// TODO Auto-generated method stub
		
			
			
			boolean status = sensormodel.getStatus();
			int floorNumber = sensormodel.getFloorNumber();
			int roomNumber = sensormodel.getRoomNumber();
			int smokeLevel = sensormodel.getSmokeLevel();
			int co2level = sensormodel.getCO2Level();
			
			final String POST_PARAMS = "{\n" +  "\"floorNumber\": \""+floorNumber+"\",\r\n"  +
        "    \"roomNumber\": \""+roomNumber+"\" \r\n}";
		
		
		
			System.out.println(POST_PARAMS);
		try {	
		URL obj = new URL("http://localhost:5000/SensorAPI/alarmSensor");
			
		HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		postConnection.setRequestMethod("POST");
		
		postConnection.setRequestProperty("Content-Type", "application/json");
		postConnection.setDoOutput(true);
		
		OutputStream os = postConnection.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		
		int responseCode = postConnection.getResponseCode();
		
		System.out.println("POST Response Code :  " + responseCode);
		
		System.out.println("POST Response Message : " + postConnection.getResponseMessage());
		if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
            postConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in .readLine()) != null) {
				response.append(inputLine);
			} in .close();
        // print result
			System.out.println(response.toString());
			
		} else {
			System.out.println("POST NOT WORKED");
		}
		} catch (Exception e) {
					e.printStackTrace();
		}
			
		
		int word = sensormodel.getRoomNumber();
		
		return word;
		
		

		
	}

	
	//Get Sensor details Implementation
	public  ArrayList<SensorModel> getSensors() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<SensorModel> xArrayList =new ArrayList<>();
		//SensorModel smm = new SensorModel();
		try {
			//Rest Api url
		URL urlForGetRequest = new URL("http://localhost:5000/SensorAPI/alarmSen");
	    String readLine = null;
	    
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");
	    

	    int responseCode = conection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	       
	    	BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	    		StringBuffer response = new StringBuffer();
	        
	        
	        while ((readLine = in .readLine()) != null) {
	            response.append(readLine);
	        } in .close();
	        
	        // print result
	        System.out.println("JSON String Result " + response.toString());
	        //GetAndPost.POSTRequest(response.toString());
	        
	        JSONArray  obj_JSONArray = new JSONArray(response.toString());
	        System.out.println("JSON Array Result " + obj_JSONArray);
	        System.out.println("JSON Array length " + obj_JSONArray.length());
	   
	        
	       //Setting respond data to object
	        for(int i=0;i<obj_JSONArray.length();i++) {
	        	
	        	SensorModel smm = new SensorModel();
	        	JSONObject jo = obj_JSONArray.getJSONObject(i);
	        	smm.setId(jo.getInt("id"));
	        	smm.setStatus(jo.getBoolean("activeStatus"));
	        	smm.setFloorNumber(Integer.parseInt(jo.getString("floorNumber")));
	        	smm.setRoomNumber(jo.getInt("roomNumber"));
	        	smm.setCO2Level(jo.getInt("co2Lvl"));
	        	smm.setSmokeLevel(jo.getInt("smokeLvl"));
	        	
	        	xArrayList.add(smm);
	        }
	    
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return xArrayList;
	}

	//Update Sensor details implementation
	public void updatesensor(SensorModel sensormodel) throws RemoteException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		
		int id = sensormodel.getId();
		boolean status = sensormodel.getStatus();
		int floorNumber = sensormodel.getFloorNumber();
		int roomNumber = sensormodel.getRoomNumber();
		int smokeLevel = sensormodel.getSmokeLevel();
		int co2level = sensormodel.getCO2Level();

		//Converting Java Object to jason string
		final String POST_PARAMS = "{\n" +  "\"id\": \""+id+"\",\r\n"  +
				"	\"activeStatus\": \""+status+"\",\r\n"  +
				"	\"floorNumber\": \""+floorNumber+"\",\r\n"  +
				"	\"co2Lvl\": \""+co2level+"\",\r\n"  +
				"	\"smokeLvl\": \""+smokeLevel+"\",\r\n"  +
				"   \"roomNumber\": \""+roomNumber+"\" \r\n}";
	
		
		
		System.out.println("chikeeey "+POST_PARAMS);
		
		
	try {	
	URL obj = new URL("http://localhost:5000/SensorAPI/alarmSensor/"+ id);
		
	HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	postConnection.setRequestMethod("PUT");
	
	postConnection.setRequestProperty("Content-Type", "application/json");
	postConnection.setDoOutput(true);
	
	OutputStream os = postConnection.getOutputStream();
	os.write(POST_PARAMS.getBytes());
	os.flush();
	os.close();
	
	int responseCode = postConnection.getResponseCode();
	
	System.out.println("POST Response Code :  " + responseCode);
	
	System.out.println("POST Response Message : " + postConnection.getResponseMessage());
	if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
		BufferedReader in = new BufferedReader(new InputStreamReader(
        postConnection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in .readLine()) != null) {
			response.append(inputLine);
		} in .close();
    // print result
		System.out.println(response.toString());
		
	} else {
		System.out.println("POST NOT WORKED");
	}
	} catch (Exception e) {
				e.printStackTrace();
	}
		
	
	
	}

}

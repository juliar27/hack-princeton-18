package org.hack.princeton.hack_princeton;

import java.io.IOException;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        try {
			System.out.println(getWalkTime("api-key","Forbes College Princeton, NJ 08540", "65 Olden St, Princeton, NJ 08540"));
			System.out.println(getBikeTime("api-key","Forbes College Princeton, NJ 08540", "65 Olden St, Princeton, NJ 08540"));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    public static double getWalkTime(String apiKey, String addrOne, String addrTwo) throws ApiException, InterruptedException, IOException{
		
    	//set up key
       	GeoApiContext distCalcer = new GeoApiContext.Builder()
    		    .apiKey(apiKey)
    		    .build();
       	
       	DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(distCalcer); 
           DistanceMatrix result = req.origins(addrOne)
                   .destinations(addrTwo)
                   .mode(TravelMode.WALKING)
                   .language("en-US")
                   .await();
           
    			double timeApart = result.rows[0].elements[0].duration.inSeconds;
    			timeApart /= 60.0;
    	
    	return timeApart;
    }
    
public static double getBikeTime(String apiKey, String addrOne, String addrTwo) throws ApiException, InterruptedException, IOException{
		
    	//set up key
       	GeoApiContext distCalcer = new GeoApiContext.Builder()
    		    .apiKey(apiKey)
    		    .build();
       	
       	DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(distCalcer); 
           DistanceMatrix result = req.origins(addrOne)
                   .destinations(addrTwo)
                   .mode(TravelMode.BICYCLING)
                   .language("en-US")
                   .await();
           
    			double timeApart = result.rows[0].elements[0].duration.inSeconds;
    			timeApart /= 60.0;
    	
    	return timeApart;
    }
}


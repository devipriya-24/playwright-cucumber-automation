package utils;

import java.io.FileReader;
import java.util.Random;
import java.util.UUID;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonReader {

    public static JSONObject getTestData() {
    	
    	
    	try {	
    		 FileReader reader = new FileReader("src/test/resources/testdata/testdata.json");
             JSONTokener token = new JSONTokener(reader);
             JSONObject fullData = new JSONObject(token);

             JSONObject resourceData = fullData.getJSONObject("resourceData");
             // Generate random values
             resourceData.put("name", generateRandomName());
             resourceData.put("email", generateRandomEmail());
             resourceData.put("phoneNumber", generateRandomPhone());
             return resourceData;

    	}catch(Exception e) {
    		throw new RuntimeException("Failed to read JSON file");
    	}
    	
    }
    private static String generateRandomName() {
    	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder name = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < 6; i++) {   // length = 6 letters
            int index = random.nextInt(alphabet.length());
            name.append(alphabet.charAt(index));
        }

        return name.toString();
    }
    
    private static String generateRandomEmail() {
        return "user" + System.currentTimeMillis() + "@test.com";
    }
    
    private static String generateRandomPhone() {
        Random random = new Random();
        long number = 9000000000L + (long)(random.nextDouble() * 1000000000L);
        return String.valueOf(number);
    }
   // JSONObject fullData = JsonReader.getTestData();
   // JSONObject data = fullData.getJSONObject("resourceData");
    }



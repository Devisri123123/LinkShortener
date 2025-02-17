package LinkShortener;

import java.util.HashMap;
import java.util.Random;

public class URLShortener {
       private HashMap<String, String> keyMap;
       private HashMap<String, String> valueMap;
       
       private String domain;
       private char myChars[];
       
       private Random myRand;
       private int keyLength;
       
       URLShortener(){
    	   keyMap = new HashMap<String, String>();
    	   valueMap = new HashMap<String, String>();
    	   myRand = new Random();
    	   keyLength = 8;
    	   myChars = new char[62];
    	   for(int i=0; i<62;i++) {
    		   int j=0;
    		   if(i< 10) {
    			   j=i+48;
    		   }else if(i>9 && i<=35){
    			  j =i+55;
    		   }else {
    			   j = i + 61;
    		   }
    		   myChars[i] = (char)j;
    	   }
    	   domain = "http://fkt.in";
       }
       
       URLShortener(int length,String newDomain){
    	   this();
    	   this.keyLength = length;
    	   if(!newDomain.isEmpty()) {
    		   domain = newDomain;
    	   }
       }
       
       public String shortenURL(String longURL) {
		   String shortURL = " ";
		   if(validateURL(longURL)) {
			   longURL = sanitizeURL(longURL);
			   if(valueMap.containsKey(longURL)) {
				   shortURL = domain + "/" + valueMap.get(longURL);
				   }
			   else {
				   shortURL = domain + "/" + getKey(longURL);
			   }
		   }
		   return shortURL;
	   }


       public String  expandURL(String shortURL) {
    	   String longURL = " ";
    	   String key = shortURL.substring(domain.length() + 1);
    	   longURL = keyMap.get(key);
    	   return longURL;
       }
       boolean validateURL(String url) {
    	   return true;
       }
       
       String sanitizeURL(String url) {
    	   if(url.substring(0,7).equals("http://"))
    		   url = url.substring(7);
    	   if(url.substring(0,8).equals("https://"))
    		   url = url.substring(8);
    	   if(url.charAt(url.length()-1)=='/')
    		   url = url.substring(0, url.length()-1);
    	   return url;
       }
       private String getKey(String longURL) {
    	   String key;
    	   key = generatekey();
    	   keyMap.put(key,  longURL);
    	   valueMap.put(longURL,  key);
    	   return key;
       }
       private String generatekey() {
    	   String key ="";
    	   boolean flag = true;
    	   while(flag) {
    		   key = "";
    		   for(int i=0;i<=keyLength;i++) {
    			   key += myChars[myRand.nextInt(62)];
    		   }
    			   if(!keyMap.containsKey(key)) {
    				   flag = false;
    			   }
    		   }
    		   return key;
    	   }
        public static void main(String[] args) {
        	URLShortener u = new URLShortener(5, "www.tinyurl.com/");
        	String urls[] = { "www.google.com/", "www.google.com", "http:/www.yahoo.com","www.yahoo.com/", "www.flipkart.in", "www.icicibank.com" };  
        	for(int i=0;i<urls.length;i++) {
        		System.out.println("URL:" + urls[i] + "\tTiny: " + u.shortenURL(urls[i]) + "\tExpanded: " + u.expandURL(u.shortenURL(urls[i])));
        		}
        }
       
}

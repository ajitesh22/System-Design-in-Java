/*package whatever //do not write package name here */

import java.io.*;

class FactroyMain {
	public static void main (String[] args) {

	/*	OS os = new Android();
		os.spec();*/
		
		OperatingSystemFactory operatingSystemFactory  = new OperatingSystemFactory();
		OS os = os.getInstance("android");
		os.spec();
		
	}
}

public class OperatingSystemFactory{
    
    public OS getIntance(String str){
        
        if(str.equals("android"))
            return new Android();
        else if(str.equals("ios"))
            return new IOS();
        else
            return new Windows();
    }   
}


public interface OS {
    
    public void spec();
}


public class Windows implements OS {
    
    @Override
    public  void spec(){
        System.out.println("Welcome to Windows OS");
    }
}


public class Android implements OS {
    
    @Override
    public  void spec(){
        System.out.println("Welcome to Android OS");
    }
}


public class IOS implements OS {
    
    @Override
    public  void spec(){
        System.out.println("Welcome to IOS OS");
    }
}

/*
FACTORY DESIGN PATTERN :
A object creational design pattern 
OperatingSystemFactory is responsible for creating object of subclass which makes object creation logic  hidden to the client
Low coupling and high cohesion

*/


import java.io.*;

//phone.java

public class phone {
    
    private String os;
    private int ram;
    private String processor;
    private double screenSize;
    private int battery;
    
    public Phone(String os , int ram , String processor , double screenSize , int battery){
        super();
        this.os = os ;
        this.ram = ram;
        this.processor = processor;
        this.screenSize = screenSize;
        this.battery = battery;
    }
    
    @Override
    public String toString() {
        return "Phone [os=" + os +" ram = " + ram + " processor = "+ processor  + " screenSize =" + screenSize  + " battery = "+ battery +"]" ;
    }
    
}



//shop.java
public class shop {
    public static void main(String args[]){
        //Phone phone = new Phone("Android" , 2 , "Snapdragon" , 5.0 , 5000); 
        // problem as we need to pass all parameters in same order even when we do not require all parameters
        
        Phone phone = new PhoneBuilder()
                            .setOs("Android")
                            .setRam(2)
                            .setBattery(4500)
                            .getPhone();
        
        Sytem.out.println(phone);
    }
}

//PhoneBuilder.java

public class PhoneBuilder {
    
    private String os;
    private int ram;
    private String processor;
    private double screenSize;
    private int battery;
    
    //If we use return type as PhoneBuilder Object instead void at run time we can specify which parameter to set
    
    public PhoneBuilder setOs(String os){
        this.os = os;
        return this;
    }
    
    public PhoneBuilder setRam(String ram){
        this.ram = ram;
        return this;
    }
    
    public PhoneBuilder setProcessor(String processor){
        this.processor = processor;
        return this;
    }
    
    public PhoneBuilder setScreenSize(String screenSize){
        this.screenSize = screenSize;
        return this;
    }

    public PhoneBuilder setBattery(String battery){
        this.battery = battery;
        return this;
    }
}


























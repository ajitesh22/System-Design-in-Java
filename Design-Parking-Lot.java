//DESIGN PARKING LOT


/*
use case 

multiple floor in the parking lot
multiple gate in the parking lot
parking attended at each gate
entry gate should process parking ticket
exit gate should process payemnt hourly basis
facilty of parking different type of vehicle
display board at each floor showing the details of vacant spots on the floor

*/

Class ParkingLot {
	
	List<ParkingFloor> parkingFloors;  
	List<Entrance> entrances;  
	List<Exit> exits;  
 
	Address address;   

	String parkingLotName;

	public boolean isParkingSpaceAvailable(Vehicle  vehicle);
	public boolean updateParkingAttendant(ParkingAttendant parkingAttendant , int gateId);

}

class ParkingFloor {

	int levelId;
	boolean isFull;
	List<ParkingSpace> parkingSpace; 

	ParkingDisplayBoard  parkingDisplayBoard;  

}

class Gate {
	int gateId;
	ParkingAttendant parkingAttendant;   
}

class Entrance extends Gate{
	public ParkingTicket getParkingTicket(Vehicle vehicle);
}

class Exit extends Gate{
	public ParkingTicket payForParkingTicket(ParkingTicket parkingTicket , PaymentType payementType);
}


class Address {

	String street;
	String city;
	String state;
	String country;
	String pinCode;
	
}


 class ParkingSpace {

 	int spaceId;
 	boolean isFree;
 	double costPerHour;
 	Vehicle vehicle;     
 	ParkingSpaceType parkingSpaceType; 
 }


class ParkingDisplayBoard {

	Map<ParkingSpaceType, Integer> freeSpotsAvailableMap;
	public void  updateFreeSpotsAvailable(ParkingSpaceType parkingSpaceType , int spaces);

}

class Account {
	String name;
	String email;
	String passWord;
	String empId;
	Address address;
}


class Admin extends Account {

	public boolean addParkingFloor(ParkingLot parkingLot , ParkingFloor parkingFloor);
	public boolean addParkingSpot(ParkingFloor parkingFloor , ParkingSpace parkingSpace);
	public boolean addParkingDisplayBoard(ParkingFloor parkingFloor , ParkingDisplayBoard  parkingDisplayBoard);

}


class ParkingAttendant extends Account {

	Payement paymentService;
	public boolean processVehicleEntry(Vehicle vehicle);
	public PaymentInfo processPayment(ParkingTicket parkingTicket , PaymentType payementType);
}


class Vehicle {

	String licenseNumber;
	VehicleType vehicleType;
	ParkingTicket parkingTicket;
	PaymentInfo paymentInfo;
}


class ParkingTicket {
	int ticketId;
	int levelId;
	int spaceId;
	Date vehicleEntryDateTime;
	Date vehicleExitDateTime;
	ParkingSpaceType parkingSpaceType;
	double totalCost;
	ParkingTicketStaus ParkingTicketStaus;

	public void updateTotalCost();
	public void updatVehicleExistTime(Date vehicleExitDateTime);
}



public Enum PaymentType {
	CASH , CREDIT_CARD , DEBIT_CARD ,UPI ;
}


public Enum ParkingSpaceType {
	BIKE_PARKING , CAR_PARKING , TRUCK_PARKING;

}



class Payment {
	public Payment makePayment(ParkingTicket parkingTicket , PaymentType paymentType)
}


public class PaymentInfo {

	double amount;
	Date paymentDate;
	int transactionId;
	ParkingStatus parkingStatus;
	PaymentStatus paymentStatus;
}


public enum VehicleType {
	BIKE , CAR , TRUCK ;
}

public enum parkingTicketStatus {
	PAID , ACTIVE ; 
}

public enum PaymentStatus {
	UNPAID , PENDING , COMPLETED , DECLINED , CANCELLED , REFUNDED;
}













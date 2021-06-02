/*

Design BookMyShow


Usecases :

User should be able to browse the movie list
User should be able search  movie based on theater and currently running.
User should be able to see movie details like rating ,Likes ,dislikes , views, description, releaseDate.
User should be able to view the seats for particular movie in a theater. done
User can select a seat and reserve and move to check out.
User should be able to book a seat.
Payment option can be integrated.
A seat should go in lock state for sometime if a user selects and proceeds to checkout.

TODO
movie list to be shown to User with show timing


*/
/*
Entity 

User
Movie
Theater
Seat

BookingService
PaymentService

*/




public Class User {
	int userId;
	String name;
	String passWord;
	String email;
	Date dob;
	Sting phoneNumber;

	Location location;

	
}

public class Location {

	String pinCode;
	String city;
	String country;

}

public class Theater {

	String name;
	Address address;

	List<Movie> movies;
	List<MovieHall> movieHalls;


	public List<Seat> getAvailableSeatsByMovie(Movie movie);

	public  boolean checkout(Movie movie  , List<Seat> seats); 

}




public class MovieList{

	List<Movie> movies;

	public List<Movie> getMoviesByTheater(Theater theater);
	public List<Movie> getCurrentShowingMovieList(Location location);
	public List<Theater> getTheaterByMovie(Location location , Movie movie);

}


public class Movie {

	String name;
	String description;
	Date releaseDate;
	int likesCount;
	int dislikeCount;

}

public class MovieHall{

	int hallNumber;
	List<Seat> seats;


}


public class Seat {


	int seatNumber;
	SeatType seatType;
	double price;
	boolean isBooked;

	public boolean isAvailble(int seatNumber);
	public void updateAvailablity(int seatNumber);

}


public enum SeatType {
	SILVER , GOLD , PLATINUM ;
}


public class Transaction {

	int transactionId;
	PaytmentType paymentType;

	double getTicketAmount(List<Seat>seats);

}


public enum PaytmentType{

	CREDIT_CARD ,DEBIT_CARD ,UPI ;
}



























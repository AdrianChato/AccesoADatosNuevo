package MongoDB.Modelos;

public class Direccion {

	private String city;
	private int zip;
	private String street;
	private int number;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Direccion [city=" + city + ", zip=" + zip + ", street=" + street + ", number=" + number + "]";
	}
	public Direccion(String city, int zip, String street, int number) {
		super();
		this.city = city;
		this.zip = zip;
		this.street = street;
		this.number = number;
	}
	
	
}

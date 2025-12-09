package MongoDB.Modelos;

public class Coordenadas {

	private float lat;
	private float lon;
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	@Override
	public String toString() {
		return "Coordenadas [lat=" + lat + ", lon=" + lon + "]";
	}
	public Coordenadas(float lat, float lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}
	
	
}

package entity;

import java.util.Optional;

import org.json.JSONObject;

public class Address {
	private Optional<String> city;
	private Optional<String> state;
	private Optional<String> country;
	public String getCity() {
		return city.orElse("City");
	}
	public void setCity(String city) {
		this.city = Optional.ofNullable(city);
	}
	public String getState() {
		return state.orElse("State");
	}
	public void setState(String state) {
		this.state = Optional.ofNullable(state);
	}
	public String getCountry() {
		return country.orElse("Country");
	}
	public void setCountry(String country) {
		this.country = Optional.ofNullable(country);
	}
	public static Address fromJson(String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		Address a = new Address();
		a.setCity(obj.getString("city"));
		a.setCountry(obj.getString("country"));
		a.setState(obj.getString("state"));
		return a;
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + ", country=" + country + "]";
	}
	
}

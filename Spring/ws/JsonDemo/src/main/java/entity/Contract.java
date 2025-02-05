package entity;

import java.util.Optional;

import org.json.JSONObject;

public class Contract {
	private Optional<String> with;
	private Optional<String> withAadhaar;
	private Optional<String> clause;
	public String getWith() {
		return with.orElse("with");
	}
	public void setWith(String with) {
		this.with = Optional.ofNullable(with);
	}
	public String getWithAadhaar() {
		return withAadhaar.orElse("aadhaar");
	}
	public void setWithAadhaar(String withAadhaar) {
		this.withAadhaar = Optional.ofNullable(withAadhaar);
	}
	public String getClause() {
		return clause.orElse("clause");
	}
	public void setClause(String clause) {
		this.clause = Optional.ofNullable(clause);
	}
	public static Contract fromJson(String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		Contract c = new Contract();
		c.setClause(obj.getString("clause"));
		c.setWith(obj.getString("with"));
		c.setWithAadhaar(obj.getString("withAadhaar"));
		return c;
	}
	@Override
	public String toString() {
		return "Contract [with=" + with + ", withAadhaar=" + withAadhaar + ", clause=" + clause + "]";
	}
	
}

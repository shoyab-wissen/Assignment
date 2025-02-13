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
		c.setClause(obj.has("clause") ? obj.getString("clause") : null);
		c.setWith(obj.has("with") ? obj.getString("with") : null);
		c.setWithAadhaar(obj.has("withAadhaar") ? obj.getString("withAadhaar") : null);
		return c;
	}
	@Override
	public String toString() {
		String toPrint = new StringBuilder()
				.append("{\n")
				.append("		With: " + getWith() + "\n")
				.append("		With Aadhaar: " + getWithAadhaar() + "\n")
				.append("		Clause: " + getClause() + "\n")
				.append("	}")
				.toString();
		return toPrint;
	}
	
}

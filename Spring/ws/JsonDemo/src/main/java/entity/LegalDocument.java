package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

public class LegalDocument {
	private Optional<String> aadhaarCard;
	private Optional<String> panNumber;
	private Optional<String> name;
	private Optional<Address> address;
	private Optional<List<Contract>> contract;
	public String getAadhaarCard() {
		return aadhaarCard.orElse("aadhaar");
	}
	public void setAadhaarCard(String aadhaarCard) {
		this.aadhaarCard = Optional.ofNullable(aadhaarCard);
	}
	public String getPanNumber() {
		return panNumber.orElse("pan card");
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = Optional.ofNullable(panNumber);
	}
	public String getName() {
		return name.orElse("name");
	}
	public void setName(String name) {
		this.name = Optional.ofNullable(name);
	}
	public Address getAddress() {
		return address.orElse(new Address());
	}
	public void setAddress(Address address) {
		this.address = Optional.ofNullable(address);
	}
	public List<Contract> getContract() {
		return contract.orElse(Collections.EMPTY_LIST);
	}
	public void setContract(List<Contract> contract) {
		this.contract = Optional.ofNullable(contract);
	}
	
	public static LegalDocument fromJson(String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		LegalDocument document = new LegalDocument();
		document.setAadhaarCard(obj.has("aadhaar") ? obj.getString("aadhaar") : null);
		document.setPanNumber(obj.has("pan") ? obj.getString("pan") : null);
		document.setName(obj.has("name") ? obj.getString("name") : null);
		JSONArray contract = new JSONArray(obj.getJSONArray("contract"));
		List<Contract> contracts = new ArrayList<>();
		contract.forEach((data)->contracts.add(Contract.fromJson(data.toString())));
		document.setContract(contracts);
		document.setAddress(Address.fromJson(new JSONObject(obj.getJSONObject("address")).toString()));
		return document;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		String toPrint = builder.append("{\n")
				.append("	Aadhaar Card: "+getAadhaarCard() + "\n")
				.append("	Pan Number: " + getPanNumber() + "\n")
				.append("	Name: " + getName() + "\n")
				.append("	Address: " + getAddress().toString() + "\n")
				.append("	Contract: " + getContract().toString() + "\n")
				.append("}")
				.toString();
		return toPrint;
	}
	
}

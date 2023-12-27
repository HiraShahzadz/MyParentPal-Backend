package com.fyp.MyParentPal.Entity;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="users")
public class User {

    @Id
    private String _id;
    private String email;
    private String password;
    private String name;
	private String dob;
	private String gender;
	private List<String> tags;
    private String role;

    public User()
    {}
   
    public User(String _id, String email, String password, String name, String dob, String gender,
			List<String> tags, String role) {
		this._id = _id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.tags = tags;
		this.role = role;
	}


	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public List<String> getTags() {
		return tags;
	}


	public void setTags(List<String> tags) {
		this.tags = tags;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [_id=" + _id + ", email=" + email + ", password=" + password + ", name=" + name + ", dob=" + dob
				+ ", gender=" + gender + ", tags=" + tags + ", role=" + role + "]";
	}


}

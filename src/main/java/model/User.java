package model;

import org.springframework.stereotype.Component;

@Component
public class User {
	private int id;
	private String name;
	private int roleId;
	private int ratings;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public User(){
		
	}
	public User(int id, String name, int role, int ratings){
		this.id = id;
		this.name = name;
		this.roleId = role;
		this.ratings = ratings;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "[id = " + id + "][name = " + name +"]" + "[roleid = " + roleId + "]";
	}
}

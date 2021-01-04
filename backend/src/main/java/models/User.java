package models;

import models.User;

public class User {
    private int id;
    private int roleId;     //may want to make an enum
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public User (int id, int roleId, String username, String firstName, String lastName, String email) {
        this.id = id;
        this.roleId = roleId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", roleId=" + roleId + ", username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + "]";
	}
}
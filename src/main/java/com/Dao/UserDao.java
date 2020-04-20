package com.Dao;

import java.util.List;

import com.Model.User;

public interface UserDao {
	
	public boolean addUser(User user);
	public boolean updateUser(User user);
	public User getUser(int id);
	public List<User>retrieveUser(int otp);
	public List<User>retrieveUser1(int id);
    public List<User> getUserByEmail(String username);
    public boolean updateUserByEmail(String username,String password);
    public long countRow(String username);
    public List<User> getNameByEmail(String username);
    public List<User> getPhoneByEmail(String username);
    public String getIdByEmail(String email);
}

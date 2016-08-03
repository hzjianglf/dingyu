package com.ticket.master.dao;

import com.ticket.master.entity.User;

public interface UserDao {
	public User getUserBySql(String sql);
}

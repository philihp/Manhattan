package com.philihp.manhattan.repo;

import java.util.List;

import com.philihp.manhattan.domain.User;

public interface UserDao
{
    public User findById(int id);

    public User findByEmail(String email);

	public User findByTwitterId(Integer twitterId);

    public List<User> findAllOrderedByName();

    public void register(User user);
}

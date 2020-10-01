package edu.escuelaing.arep.repository;

import edu.escuelaing.arep.entities.User;

public interface UserDAO {

    public User getUSerByName(String name);
}

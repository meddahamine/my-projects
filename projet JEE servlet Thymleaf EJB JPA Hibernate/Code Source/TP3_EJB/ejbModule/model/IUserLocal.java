package model;

import javax.ejb.Local;

@Local
public interface IUserLocal {

	boolean verify(String email);
}

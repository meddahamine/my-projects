package model;

import java.util.ArrayList;

import javax.ejb.Stateless;

@Stateless(name="USER")
public class UserEJBImpl implements IUserLocal{
	
	private ArrayList<String> userList = new ArrayList() {{ add("a@gmail.com"); add("b@gmail.com"); add("c@gmail.com"); }};
	private boolean bool=false;
	
	@Override
	public boolean verify(String email) {
		for (String s : userList) {
			if (email.equals(s)) {
				bool = true;
			}
		}
		return bool;
	}

}

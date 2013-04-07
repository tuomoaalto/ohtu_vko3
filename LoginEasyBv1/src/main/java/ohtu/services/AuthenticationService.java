package ohtu.services;

import ohtu.domain.User;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private UserDao userDao;

	@Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
		boolean retVal = false;
		Pattern specialCharsAndNumbers = Pattern.compile("[^A-Za-z]");
		
		
		//Username length
		if ( username.length() < 3 ){
			retVal = true;
		}
		
		//Username contents are a to z
		for (int i = 0; i < username.length(); i++) {
			if (Character.isAlphabetic(username.charAt(i)) == false)
			{
				retVal = true;
			}
		}
		
		//Password length
		if ( password.length() < 8 ){
			retVal = true;
		}		

		//Password complexity
		if (specialCharsAndNumbers.matcher(password).find() != true)
		{
			retVal = true;
		}
		
        return retVal;
    }
}

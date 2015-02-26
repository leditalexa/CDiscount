package fr.eisti.pau.cdiscount.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.eisti.pau.cdiscount.dao.UserDao;
import fr.eisti.pau.cdiscount.domain.User;
import fr.eisti.pau.cdiscount.exception.UserAlreadyExistsException;
import fr.eisti.pau.cdiscount.exception.WrongPasswordException;
import fr.eisti.pau.cdiscount.exception.WrongUserException;

public class UserService {

	private final UserDao userDao = new UserDao();

	public User get(String identifiant){
		return userDao.get(identifiant);
	}

	public User signUp(User user)throws WrongUserException, UserAlreadyExistsException{
		if(user.getPassword()!=null){
			user.setPassword(generateHash(user.getPassword()));
			return userDao.create(user);
		}else{
			throw new WrongUserException();
		}
	}

	public User login(String identifiant, String password) throws WrongPasswordException, WrongUserException{

		User usr = userDao.get(identifiant);

		if(usr != null){
			if(generateHash(password).equals(usr.getPassword())){
				return usr;
			}else{
				throw new WrongPasswordException();
			}
		}else{
			throw new WrongUserException();
		}
	}


	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}
		return hash.toString();
	}

}

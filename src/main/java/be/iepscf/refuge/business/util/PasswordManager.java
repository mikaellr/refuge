package be.iepscf.refuge.business.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import be.iepscf.refuge.business.businessbean.User;

public class PasswordManager {

	public String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    	String hex = Integer.toHexString(0xff & hash[i]);
	    	if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	public String hash(String input) {
		try {
			MessageDigest digest;
			digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
			String hash = bytesToHex(encodedhash);
			return hash;
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}
	
	public int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public String salt(int size) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0 ; i < size ; i++) {
			char c = (char) ('a' + getRandomNumberInRange(0, 25));
			buffer.append(c);
		}
		return buffer.toString();
	}
	
	
	
	public String hashPasswordAndSalt(String password, String salt) {
		String hash = '*' + hash(password.trim() + salt) + '*';
		return hash;
	}
	
	public void setUserPassword(User user, String password) {
		if (password == null) {
			throw new IllegalArgumentException("Password null");
		}
		if (password.trim().equals("")) {
			throw new IllegalArgumentException("Password empty");
		}
		String salt = salt(255);
		if (salt == null) {
			throw new IllegalArgumentException("Salt null");
		}
		if (salt.trim().equals("")) {
			throw new IllegalArgumentException("Salt empty");
		}
		String hash = hashPasswordAndSalt(password, salt);
		if (hash == null) {
			throw new IllegalArgumentException("Hash null");
		}
		if (hash.trim().equals("")) {
			throw new IllegalArgumentException("Hash empty");
		}
		user.setSalt(salt);
		user.setHash(hash);
	}

	public void setUserPassword(be.iepscf.refuge.persistence.entitybean.User user, String password) {
		if (password == null) {
			throw new IllegalArgumentException("Password null");
		}
		if (password.trim().equals("")) {
			throw new IllegalArgumentException("Password empty");
		}
		String salt = salt(255);
		if (salt == null) {
			throw new IllegalArgumentException("Salt null");
		}
		if (salt.trim().equals("")) {
			throw new IllegalArgumentException("Salt empty");
		}
		String hash = hashPasswordAndSalt(password, salt);
		if (hash == null) {
			throw new IllegalArgumentException("Hash null");
		}
		if (hash.trim().equals("")) {
			throw new IllegalArgumentException("Hash empty");
		}
		user.setSalt(salt);
		user.setHash(hash);
	}

	public boolean checkUserPassword(User user, String password) {
		if (user != null) {
			String salt = user.getSalt();
			String hash = user.getHash();
			if (password == null) {
				throw new IllegalArgumentException("Password null");
			}
			if (password.trim().equals("")) {
				throw new IllegalArgumentException("Password empty");
			}
			if (salt == null) {
				throw new IllegalArgumentException("Salt null");
			}
			if (salt.trim().equals("")) {
				throw new IllegalArgumentException("Salt empty");
			}
			if (hash == null) {
				throw new IllegalArgumentException("Hash null");
			}
			if (hash.trim().equals("")) {
				throw new IllegalArgumentException("Hash empty");
			}
			if (hash.contentEquals(hashPasswordAndSalt(password, salt))) {
				return true;
			}
		}
		return false;
	}

	public boolean checkUserPassword(be.iepscf.refuge.persistence.entitybean.User user, String password) {
		if (user != null) {
			String salt = user.getSalt();
			String hash = user.getHash();
			if (password == null) {
				throw new IllegalArgumentException("Password null");
			}
			if (password.trim().equals("")) {
				throw new IllegalArgumentException("Password empty");
			}
			if (salt == null) {
				throw new IllegalArgumentException("Salt null");
			}
			if (salt.trim().equals("")) {
				throw new IllegalArgumentException("Salt empty");
			}
			if (hash == null) {
				throw new IllegalArgumentException("Hash null");
			}
			if (hash.trim().equals("")) {
				throw new IllegalArgumentException("Hash empty");
			}
			if (hash.contentEquals(hashPasswordAndSalt(password, salt))) {
				return true;
			}
		}
		return false;
	}
}

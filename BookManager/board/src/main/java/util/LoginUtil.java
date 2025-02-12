package util;

import java.security.MessageDigest;

import net.pwall.util.Base64;



public class LoginUtil {
	
	public static String encryptPassword(String id, String pw) throws Exception{
		
		if(pw == null){
			return "";
		}
		
		byte[] hashValue = null;
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		
		md.reset();
		md.update(id.getBytes());
		
		hashValue = md.digest(pw.getBytes());
		
		
		return new String(Base64.encode(hashValue));
	}
}

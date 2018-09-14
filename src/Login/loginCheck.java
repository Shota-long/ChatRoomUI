package Login;

public class loginCheck {

	String userName,userPwd;
	
	public loginCheck(String userName, String userPwd) {
		// TODO Auto-generated constructor stub
		this.userName = userName;
		this.userPwd = userPwd;
	}

	public boolean loginCheck() {
		// TODO Auto-generated constructor stub
		if(userName.equals("long")&userPwd.equals("123456"))
			return true;
		else return false;
		
	}


}

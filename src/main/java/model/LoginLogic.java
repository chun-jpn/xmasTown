package model;
//ログインに関する処理を行うモデル
public class LoginLogic {
	public boolean execute(User user) {
//		パスワードは「1234」固定
		if(user.getPass().equals("1234")) {
			return true;
		}
		return false;
	}
}

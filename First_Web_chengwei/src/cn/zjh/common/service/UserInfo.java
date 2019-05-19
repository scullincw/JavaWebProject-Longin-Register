package cn.zjh.common.service;

public class UserInfo {
	
	private String account;
	private String password;
	private String tel;
	private String email;
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//使用account作为该用户的标识，所以根据account判断是否是同一用户
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj != null && obj.getClass() == UserInfo.class)
		{
			UserInfo target = (UserInfo)obj;
			if (account != null)
			{
				return account.equals(target.getAccount());
			}
		}
		return false;
	}
}

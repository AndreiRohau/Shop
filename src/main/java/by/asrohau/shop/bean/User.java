package by.asrohau.shop.bean;

public class User {

	private int id;
	private String login;
	private String password;
	private String newPassword;

	public User(){}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public User(int id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public User(String login, String password, String newPassword) {
		this.login = login;
		this.password = password;
		this.newPassword = newPassword;
	}

	public User(int id, String login, String password, String newPassword) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.newPassword = newPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (login != null ? !login.equals(user.login) : user.login != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		return newPassword != null ? newPassword.equals(user.newPassword) : user.newPassword == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (newPassword != null ? newPassword.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", newPassword='" + newPassword + '\'' +
				'}';
	}
}

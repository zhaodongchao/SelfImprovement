package com.newtouch.entitys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class User implements UserDetails{

  private static final long serialVersionUID = 2388732858987583830L;
  private String user_id;
  private String username ;
  private String password ;
  private String nickname ;
  private String age ;
  private boolean sex ;
  private boolean status ;
  private int login_err_count ;
  private Date birthday ;
  private String telPhone ;
  private String company ;
  private String address ;
  private String mail ;
  private Date register_time ;
  private Date last_login_time ;
  private Integer parentId ;
  
   //一个集合roles，初始容量为0
  private Set<Role> roles = new HashSet<Role>();
  //一个权限集合，这个用户所有的权限
  private Collection<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	public User() {}
	public User(String user_id, String username, String password, String nickname, String age, boolean sex,
			boolean status, int login_err_count, Set<Role> roles) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.age = age;
		this.sex = sex;
		this.status = status;
		this.login_err_count = login_err_count;
		this.roles = roles;
	}
	
	
	public User(String user_id, String username, String password, String nickname, String age, boolean sex,
			boolean status, int login_err_count, Date birthday, String telPhone, String company, String address,
			String mail, Date register_time, Date last_login_time, Integer parentId, Set<Role> roles,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.age = age;
		this.sex = sex;
		this.status = status;
		this.login_err_count = login_err_count;
		this.birthday = birthday;
		this.telPhone = telPhone;
		this.company = company;
		this.address = address;
		this.mail = mail;
		this.register_time = register_time;
		this.last_login_time = last_login_time;
		this.parentId = parentId;
		this.roles = roles;
		this.authorities = authorities;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getLogin_err_count() {
		return login_err_count;
	}
	public void setLogin_err_count(int login_err_count) {
		this.login_err_count = login_err_count;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", nickname="
				+ nickname + ", age=" + age + ", sex=" + sex + ", status=" + status + ", login_err_count="
				+ login_err_count + ", birthday=" + birthday + ", telPhone=" + telPhone + ", company=" + company
				+ ", address=" + address + ", mail=" + mail + ", register_time=" + register_time + ", last_login_time="
				+ last_login_time + ", parentId=" + parentId + ", roles=" + roles + ", authorities=" + authorities
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((last_login_time == null) ? 0 : last_login_time.hashCode());
		result = prime * result + login_err_count;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((register_time == null) ? 0 : register_time.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + (sex ? 1231 : 1237);
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((telPhone == null) ? 0 : telPhone.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (last_login_time == null) {
			if (other.last_login_time != null)
				return false;
		} else if (!last_login_time.equals(other.last_login_time))
			return false;
		if (login_err_count != other.login_err_count)
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (register_time == null) {
			if (other.register_time != null)
				return false;
		} else if (!register_time.equals(other.register_time))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (sex != other.sex)
			return false;
		if (status != other.status)
			return false;
		if (telPhone == null) {
			if (other.telPhone != null)
				return false;
		} else if (!telPhone.equals(other.telPhone))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	

  
}

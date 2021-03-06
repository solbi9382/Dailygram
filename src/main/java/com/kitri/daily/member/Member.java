package com.kitri.daily.member;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class Member {
	private String id;
	private String pwd;
	private String name;
	private Date birthday;
	private String email;
	private String zip_code;
	private String address;
	private String gender;
	private Date joindate;
	private String useyn;
	private String profile_img;
	private String publicyn;
	private String intro;
	
	private MultipartFile file;
	private String captcha;
	private int cnt;
	
	public Member() {}
	public Member(String id) {
		super();
		this.id = id;
	}
	
	//zip_code 추가된 전체 생성자
	public Member(String id, String pwd, String name, Date birthday, String email, String zip_code, String address,
			String gender, Date joindate, String useyn, String profile_img, String publicyn, String intro,
			MultipartFile file, String captcha, int cnt) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.zip_code = zip_code;
		this.address = address;
		this.gender = gender;
		this.joindate = joindate;
		this.useyn = useyn;
		this.profile_img = profile_img;
		this.publicyn = publicyn;
		this.intro = intro;
		this.file = file;
		this.captcha = captcha;
		this.cnt = cnt;
	}
	public Member(String id, String pwd, String name, Date birthday, String email, String address, String gender,
			Date joindate, String useyn, String profile_img, String publicyn, String intro) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.joindate = joindate;
		this.useyn = useyn;
		this.profile_img = profile_img;
		this.publicyn = publicyn;
		this.intro = intro;
	}
	public Member(String id, String pwd, String name, Date birthday, String email, String address, String gender,
			Date joindate, String useyn, String profile_img, String publicyn, String intro, String captcha) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.joindate = joindate;
		this.useyn = useyn;
		this.profile_img = profile_img;
		this.publicyn = publicyn;
		this.intro = intro;
		this.captcha = captcha;
	}
	public Member(String id, String pwd, String name, Date birthday, String email, String address, String gender,
			Date joindate, String useyn, String profile_img, String publicyn, String intro, int cnt) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.joindate = joindate;
		this.useyn = useyn;
		this.profile_img = profile_img;
		this.publicyn = publicyn;
		this.intro = intro;
		this.cnt = cnt;
	}
	public Member(String zip_code, String address) {
		super();
		this.zip_code = zip_code;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public String getPublicyn() {
		return publicyn;
	}
	public void setPublicyn(String publicyn) {
		this.publicyn = publicyn;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", pwd=" + pwd + ", name=" + name + ", birthday=" + birthday + ", email=" + email
				+ ", zip_code=" + zip_code + ", address=" + address + ", gender=" + gender + ", joindate=" + joindate
				+ ", useyn=" + useyn + ", profile_img=" + profile_img + ", publicyn=" + publicyn + ", intro=" + intro
				+ ", file=" + file + ", captcha=" + captcha + ", cnt=" + cnt + "]";
	}
	
	
}

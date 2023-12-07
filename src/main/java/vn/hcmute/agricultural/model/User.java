package vn.hcmute.agricultural.model;
import javax.persistence.*;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String phoneNumber;
    private String userPassword;
    private String email;
    private String fullName;
    private Date birthDate;
    private Integer gender;
    private String avatar;
    private Integer roles;
    private Date createdDate;
    private Integer userStatus;
    private String address;

    public User() {
    }

    public User(Integer userId, String phoneNumber, String userPassword, String email, String fullName, Date birthDate, Integer gender, String avatar, Integer roles, Date createdDate, Integer userStatus, String address) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.userPassword = userPassword;
        this.email = email;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.avatar = avatar;
        this.roles = roles;
        this.createdDate = createdDate;
        this.userStatus = userStatus;
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getRoles() {
        return roles;
    }

    public void setRoles(Integer roles) {
        this.roles = roles;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

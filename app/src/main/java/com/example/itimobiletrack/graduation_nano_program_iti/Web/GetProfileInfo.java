package com.example.itimobiletrack.graduation_nano_program_iti.Web;

/**
 * Created by Mahmoud on 19/03/2017.
 */

public class GetProfileInfo {

    private String  typeNameVar;
    private String  emailVar ;
    private  String user_id;
    private  String userName;
    private String password;
    private String address;
    private String phone;
    private  String charity_parent_id;

    public String getCharity_parent_id() {
        return charity_parent_id;
    }

    public void setCharity_parent_id(String charity_parent_id) {
        this.charity_parent_id = charity_parent_id;
    }

    public String getType() {
        return type;

    }

    public void setType(String type) {
        this.type = type;
    }

    private  String type;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmailVar() {
        return emailVar;
    }

    public void setEmailVar(String emailVar) {
        this.emailVar = emailVar;
    }





    public void setTypeNameVar(String typeNameVar) {
        this.typeNameVar = typeNameVar;

    }


    public String getTypeNameVar() {

        return  this.typeNameVar;

    }


}

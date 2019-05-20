package com.android.firebasedbstorage;

public class Member {
   private String name,email,pass,confirmpass,Imageid;
   private Long phone;

    public String getName() {
        return name;
    }

  /*  public String getImageid() {
        return Imageid;
    }

    public void setImageid(String imageid) {
        Imageid = imageid;
    }*/

    public Long getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    public Long getPhone(int ph) {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Member() {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.confirmpass = confirmpass;
        this.phone = phone;
    }
}

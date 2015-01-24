package com.example.lebars_r.epiandroid;

import android.media.Image;

/**
 * Created by the cambio on 23/01/2015.
 */
public class User {

    private String Login;
    private String Password;
    private String Token;
    private String  Photo;

    public void setLogin(String _log){
        Login = _log;
    }
    public void setPassword(String _pass){
        Password = _pass;
    }
    public void setToken(String _token){
        Token = _token;
    }
    public void setPhoto(String _photo){
        Photo = _photo;
    }


    public String getToken(){
        return (Token);
    }
    public  String getLogin(){
        return (Login);
    }
    public String getPassword(){
        return (Password);
    }
    public String getPhoto() {
        return Photo;
    }
}

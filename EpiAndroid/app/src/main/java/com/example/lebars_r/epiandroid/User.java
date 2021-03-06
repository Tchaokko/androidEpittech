package com.example.lebars_r.epiandroid;

import android.graphics.drawable.Drawable;
import android.media.Image;

/**
 * Created by the cambio on 23/01/2015.
 */
public class User {

    private String Login;
    private String Password;
    private String Token;
    private String Photo;
    private String uid;
    private String logtime;
    private String gid;
    private String semester;
    private String ip;
    private boolean isSetup = false;
    private  boolean failSetup = false;
    private Drawable img;
    private String notification;

    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
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
    public void setGid(String gid) {
        this.gid = gid;
    }
    public void setIsSetup(boolean bool){
        isSetup = bool;
    }
    public void setImg(Drawable _img) {
        img = _img;
    }
    public void setNotification(String str){
        notification = str;
    }
    public void setFailSetup(boolean failSetup) {
        this.failSetup = failSetup;
    }

    public String getIp() {
        return ip;
    }
    public String getSemester() {
        return semester;
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
    public String getUid() {
        return uid;
    }
    public String getLogtime() {
        return logtime;
    }
    public String getGid() {
        return gid;
    }
    public boolean getIsSetup(){
        return isSetup;
    }
    public Drawable getImg(){
        return img;
    }
    public String getNotification(){
        return notification;
    }
    public boolean isFailSetup() {
        return failSetup;
    }

}

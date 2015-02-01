package com.example.lebars_r.epiandroid;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lebars_r on 30/01/2015.
 */
public class Model implements Serializable{
    User _user = new User();
    Marks _marks = new Marks();
    Schedule _schedule = new Schedule();

    /*
     * Setter getter User
     */
    public void setIp(String ip) {
        _user.setIp(ip);
    }
    public void setSemester(String semester) {
        _user.setSemester(semester);
    }
    public void setLogtime(String logtime) {
        _user.setLogtime(logtime);
    }
    public void setUid(String uid) {
        _user.setUid(uid);
    }
    public void setLogin(String _log){
        _user.setLogin(_log);
    }
    public void setPassword(String _pass){
        _user.setPassword(_pass);
    }
    public void setToken(String _token){
        _user.setToken(_token);
    }
    public void setPhoto(String _photo){
        _user.setPhoto(_photo);
    }
    public void setGid(String gid) {
        _user.setGid(gid);
    }
    public void setIsSetupUser(boolean bool){
        _user.setIsSetup(bool);
    }
    public void setUserPicture(Drawable _img){
        _user.setImg(_img);
    }
    public void setNotification(String str){
        _user.setNotification(str);
    }
    public void setFailSetup(boolean failSetup) {
        _user.setFailSetup(failSetup);
    }

    public String getIp() {
        return _user.getIp();
    }
    public String getSemester() {
        return _user.getSemester();
    }
    public String getToken(){
        return _user.getToken();
    }
    public  String getLogin(){
        return _user.getLogin();
    }
    public String getPassword(){
        return _user.getPassword();
    }
    public String getPhoto() {
        return _user.getPhoto();
    }
    public String getUid() {
        return _user.getUid();
    }
    public String getLogtime() {
        return _user.getLogtime();
    }
    public String getGid() {
        return _user.getGid();
    }
    public boolean isUserSetup(){
        return _user.getIsSetup();
    }
    public Drawable getUserPicture(){
        return _user.getImg();
    }
    public String getNotification(){
        return _user.getNotification();
    }
    public boolean isFailSetup() {
        return _user.isFailSetup();
    }

/*
 *  Setter Getter Mark
 */
    public void putItemInList(String item){
        _marks.putItemInList(item);
    }

    public String getItemInList(int i){
        return _marks.getItemInList(i);
    }
    public int getLengthFromMarks(){
        return _marks.getLength();
    }
    public ArrayList<String> getListFromMark(){
        return _marks.getList();}

/*
 * Setter Getter Schedule
 */
    public String getWeekStart(int add){
    return _schedule.getWeekStart(add);
}
    public String getWeekEnd(int add){
        return _schedule.getWeekEnd(add);
    }

}

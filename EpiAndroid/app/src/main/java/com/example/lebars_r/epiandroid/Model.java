package com.example.lebars_r.epiandroid;

/**
 * Created by lebars_r on 30/01/2015.
 */
public class Model {
    User _user = new User();

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
}

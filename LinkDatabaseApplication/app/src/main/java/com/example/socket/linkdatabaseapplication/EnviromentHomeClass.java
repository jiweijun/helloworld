package com.example.socket.linkdatabaseapplication;

public class EnviromentHomeClass {


    private String types;
    private String  location;
    private double  DevSerialNumber;
    private String   time;

    private double  temp;//温度
    private double  ph;
    private double  rongJy;//溶解氧
    private double  dianD;//电导率
    private double  zhuD;//浊度
    private double  gaoMengSY;//高猛酸盐
    private double  cod;//cod
    private double  bod5;//BOD5
    private double  anDan;//氨氮
    private double  total_lin;//总磷
    private double  total_dan;//总氮
    private double  yuL;//余氯
    private double  ringDu;//硬度
    private double  seD;//色度
    private double  xiaoSuanY;//硝酸盐
    private double  xuanFuw;//悬浮物
    private double  shiYou;//石油

    public String  getTypes() {
        return types;
    }

    public void setTypes(String  types) {
        this.types = types;
    }

    public String  getLocation() {
        return location;
    }

    public void setLocation(String  location) {
        this.location = location;
    }

    public double getDevSerialNumber() {
        return DevSerialNumber;
    }

    public void setDevSerialNumber(double devSerialNumber) {
        DevSerialNumber = devSerialNumber;
    }

    public String  getTime() {
        return time;
    }

    public void setTime(String  time) {
        this.time = time;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getRongJy() {
        return rongJy;
    }

    public void setRongJy(double rongJy) {
        this.rongJy = rongJy;
    }

    public double getDianD() {
        return dianD;
    }

    public void setDianD(double dianD) {
        this.dianD = dianD;
    }

    public double getZhuD() {
        return zhuD;
    }

    public void setZhuD(double zhuD) {
        this.zhuD = zhuD;
    }

    public double getGaoMengSY() {
        return gaoMengSY;
    }

    public void setGaoMengSY(double gaoMengSY) {
        this.gaoMengSY = gaoMengSY;
    }

    public double getCod() {
        return cod;
    }

    public void setCod(double cod) {
        this.cod = cod;
    }

    public double getBod5() {
        return bod5;
    }

    public void setBod5(double bod5) {
        this.bod5 = bod5;
    }

    public double getAnDan() {
        return anDan;
    }

    public void setAnDan(double anDan) {
        this.anDan = anDan;
    }

    public double getTotal_lin() {
        return total_lin;
    }

    public void setTotal_lin(double total_lin) {
        this.total_lin = total_lin;
    }

    public double getTotal_dan() {
        return total_dan;
    }

    public void setTotal_dan(double total_dan) {
        this.total_dan = total_dan;
    }

    public double getYuL() {
        return yuL;
    }

    public void setYuL(double yuL) {
        this.yuL = yuL;
    }

    public double getRingDu() {
        return ringDu;
    }

    public void setRingDu(double ringDu) {
        this.ringDu = ringDu;
    }

    public double getSeD() {
        return seD;
    }

    public void setSeD(double seD) {
        this.seD = seD;
    }

    public double getXiaoSuanY() {
        return xiaoSuanY;
    }

    public void setXiaoSuanY(double xiaoSuanY) {
        this.xiaoSuanY = xiaoSuanY;
    }

    public double getXuanFuw() {
        return xuanFuw;
    }

    public void setXuanFuw(double xuanFuw) {
        this.xuanFuw = xuanFuw;
    }

    public double getShiYou() {
        return shiYou;
    }

    public void setShiYou(double shiYou) {
        this.shiYou = shiYou;
    }

    public double getyLSu() {
        return yLSu;
    }

    public void setyLSu(double yLSu) {
        this.yLSu = yLSu;
    }

    public EnviromentHomeClass(String  types, String  location, double devSerialNumber, String  time, double temp, double ph, double rongJy, double dianD, double zhuD, double gaoMengSY, double cod, double bod5, double anDan, double total_lin, double total_dan, double yuL, double ringDu, double seD, double xiaoSuanY, double xuanFuw, double shiYou, double yLSu) {

        this.types = types;
        this.location = location;
        DevSerialNumber = devSerialNumber;
        this.time = time;
        this.temp = temp;
        this.ph = ph;
        this.rongJy = rongJy;
        this.dianD = dianD;
        this.zhuD = zhuD;
        this.gaoMengSY = gaoMengSY;
        this.cod = cod;
        this.bod5 = bod5;
        this.anDan = anDan;
        this.total_lin = total_lin;
        this.total_dan = total_dan;
        this.yuL = yuL;
        this.ringDu = ringDu;
        this.seD = seD;
        this.xiaoSuanY = xiaoSuanY;
        this.xuanFuw = xuanFuw;
        this.shiYou = shiYou;
        this.yLSu = yLSu;
    }

    public EnviromentHomeClass() {

    }

    private double  yLSu;//叶绿素

}

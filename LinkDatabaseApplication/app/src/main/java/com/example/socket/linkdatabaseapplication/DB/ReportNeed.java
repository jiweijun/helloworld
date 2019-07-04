package com.example.socket.linkdatabaseapplication.DB;

public class ReportNeed {
    private String reportName;
    private String  tiName;
    private String  tiTime;
    private String  reportNo;
    public String  getReportName() {
        return reportName;
    }

    public void setReportName(String  Reportname) {
        this.reportName = Reportname;
    }

    public String  getTiName() {
        return  tiName;
    }

    public void setTiName(String   TiName) {
        this.tiName =  TiName;
    }


    public String  getTiTime() {
        return  tiTime;
    }

    public void setTiTime(String   TiTime) {
        this.tiTime =  TiTime;
    }


    public String  getReportNo() {
        return  reportNo;
    }

    public void setReportNo(String  reportNo) {
        this.reportNo =  reportNo;
    }


    public ReportNeed(String ReportName, String  tiName,String tiTime,String reportNo) {

        this.reportName = ReportName;
        this.tiName =tiName;
        this.tiTime =tiTime;
        this.reportNo=reportNo;
    }

}

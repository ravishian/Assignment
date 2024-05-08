package com.soni5.assignment.Model;

public class Model_Jobs {
    String site;
    String siteAddress;
    String siteImage;
    String siteType;
    String dutyNumber;
    String checkinTime;
    String checkoutTime;
    String pdfBill;

    public Model_Jobs() {
    }

    public Model_Jobs(String site, String siteAddress, String siteImage, String siteType, String dutyNumber, String checkinTime, String checkoutTime, String pdfBill) {
        this.site = site;
        this.siteAddress = siteAddress;
        this.siteImage = siteImage;
        this.siteType = siteType;
        this.dutyNumber = dutyNumber;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.pdfBill = pdfBill;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getSiteImage() {
        return siteImage;
    }

    public void setSiteImage(String siteImage) {
        this.siteImage = siteImage;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getDutyNumber() {
        return dutyNumber;
    }

    public void setDutyNumber(String dutyNumber) {
        this.dutyNumber = dutyNumber;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public String getPdfBill() {
        return pdfBill;
    }

    public void setPdfBill(String pdfBill) {
        this.pdfBill = pdfBill;
    }
}

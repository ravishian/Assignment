package com.soni5.assignment.Model;

public class ModelDownload
{
    String TCOLUMN_JOB,COLUMN_ADDRESS,COLUMN_LINK,COLUMN_NAME,COLUMN_EMAIL ;

    public ModelDownload() {
    }

    public ModelDownload(String TCOLUMN_JOB, String COLUMN_ADDRESS, String COLUMN_LINK, String COLUMN_NAME, String COLUMN_EMAIL) {
        this.TCOLUMN_JOB = TCOLUMN_JOB;
        this.COLUMN_ADDRESS = COLUMN_ADDRESS;
        this.COLUMN_LINK = COLUMN_LINK;
        this.COLUMN_NAME = COLUMN_NAME;
        this.COLUMN_EMAIL = COLUMN_EMAIL;
    }

    public String getTCOLUMN_JOB() {
        return TCOLUMN_JOB;
    }

    public void setTCOLUMN_JOB(String TCOLUMN_JOB) {
        this.TCOLUMN_JOB = TCOLUMN_JOB;
    }

    public String getCOLUMN_ADDRESS() {
        return COLUMN_ADDRESS;
    }

    public void setCOLUMN_ADDRESS(String COLUMN_ADDRESS) {
        this.COLUMN_ADDRESS = COLUMN_ADDRESS;
    }

    public String getCOLUMN_LINK() {
        return COLUMN_LINK;
    }

    public void setCOLUMN_LINK(String COLUMN_LINK) {
        this.COLUMN_LINK = COLUMN_LINK;
    }

    public String getCOLUMN_NAME() {
        return COLUMN_NAME;
    }

    public void setCOLUMN_NAME(String COLUMN_NAME) {
        this.COLUMN_NAME = COLUMN_NAME;
    }

    public String getCOLUMN_EMAIL() {
        return COLUMN_EMAIL;
    }

    public void setCOLUMN_EMAIL(String COLUMN_EMAIL) {
        this.COLUMN_EMAIL = COLUMN_EMAIL;
    }
}

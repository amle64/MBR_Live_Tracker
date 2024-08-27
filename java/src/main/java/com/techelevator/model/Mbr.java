package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Mbr {

    private int mbrId;
    private String productName;
    private int totalQuantityIssued;
    private int totalQuantityMade;
    private int reviewStatus;
    private LocalDate dateCreated;
    private LocalDateTime dateTimeRan;

    public Mbr(){}

    public Mbr(int mbrId, String productName, int totalQuantityIssued, int totalQuantityMade,
               int reviewStatus, LocalDate dateCreated, LocalDateTime dateTimeRan){
        this.mbrId = mbrId;
        this.productName = productName;
        this.totalQuantityIssued = totalQuantityIssued;
        this.totalQuantityMade = totalQuantityMade;
        this.reviewStatus = reviewStatus;
        this.dateCreated = dateCreated;
        this.dateTimeRan = dateTimeRan;
    }


    public int getMbrId() {
        return mbrId;
    }

    public void setMbrId(int mbrId) {
        this.mbrId = mbrId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getTotalQuantityIssued() {
        return totalQuantityIssued;
    }

    public void setTotalQuantityIssued(int totalQuantityIssued) {
        this.totalQuantityIssued = totalQuantityIssued;
    }

    public int getTotalQuantityMade() {
        return totalQuantityMade;
    }

    public void setTotalQuantityMade(int totalQuantityMade) {
        this.totalQuantityMade = totalQuantityMade;
    }

    public int getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(int reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateTimeRan() {
        return dateTimeRan;
    }

    public void setDateTimeRan(LocalDateTime dateTimeRan) {
        this.dateTimeRan = dateTimeRan;
    }
}

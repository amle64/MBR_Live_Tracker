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
    private LocalDateTime dateTimeStarted;
    private LocalDateTime dateTimeFinished;

    public Mbr(){}

    public Mbr(int mbrId, String productName, int totalQuantityIssued, int totalQuantityMade,
               int reviewStatus, LocalDate dateCreated, LocalDateTime dateTimeStarted, LocalDateTime dateTimeFinished){
        this.mbrId = mbrId;
        this.productName = productName;
        this.totalQuantityIssued = totalQuantityIssued;
        this.totalQuantityMade = totalQuantityMade;
        this.reviewStatus = reviewStatus;
        this.dateCreated = dateCreated;
        this.dateTimeStarted = dateTimeStarted;
        this.dateTimeFinished = dateTimeFinished;

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

    public LocalDateTime getDateTimeStarted() {
        return dateTimeStarted;
    }

    public void setDateTimeStarted(LocalDateTime dateTimeStarted) {
        this.dateTimeStarted = dateTimeStarted;
    }

    public LocalDateTime getDateTimeFinished() {
        return dateTimeFinished;
    }

    public void setDateTimeFinished(LocalDateTime dateTimeFinished) {
        this.dateTimeFinished = dateTimeFinished;
    }
}

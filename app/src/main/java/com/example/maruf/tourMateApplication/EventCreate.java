package com.example.maruf.tourMateApplication;

public class EventCreate {
    private String eventName;
    private String fromDate;
    private String toDate;
    private String estimatedBudget;

    public String getEventName() {
        return eventName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public String getEstimatedBudget() {
        return estimatedBudget;
    }

    public EventCreate() {
    }

    public EventCreate(String eventName, String fromDate, String toDate, String estimatedBudget) {

        this.eventName = eventName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.estimatedBudget = estimatedBudget;
    }
}

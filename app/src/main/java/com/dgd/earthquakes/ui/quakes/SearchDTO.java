package com.dgd.earthquakes.ui.quakes;

import android.text.TextUtils;

/**
 * Created by max
 * on 2/2/18.
 */

public class SearchDTO {

    public final String query;
    public final String fromTime;
    public final String fromDate;
    public final String fromMagnitude;
    public final String toTime;
    public final String toDate;
    public final String toMagnitude;

    static SearchParamsBuilder createBuilder(){
        return new SearchParamsBuilder();
    }

    private SearchDTO(String query, String fromTime, String fromDate, String fromMagnitude, String toTime,
                      String toDate, String toMagnitude) {
        this.query = query;
        this.fromTime = fromTime;
        this.fromDate = fromDate;
        this.fromMagnitude = fromMagnitude;
        this.toTime = toTime;
        this.toDate = toDate;
        this.toMagnitude = toMagnitude;
    }

    boolean isEmpty() {
        return TextUtils.isEmpty(query)
                && TextUtils.isEmpty(fromTime) && TextUtils.isEmpty(fromDate)
                && TextUtils.isEmpty(fromMagnitude) && TextUtils.isEmpty(toTime)
                && TextUtils.isEmpty(toDate) && TextUtils.isEmpty(toMagnitude);
    }



    static class SearchParamsBuilder {

        private String query;
        private String fromTime;
        private String fromDate;
        private String toTime;
        private String toDate;
        private String fromMagnitude;
        private String toMagnitude;

        private SearchParamsBuilder() {}

        SearchDTO build(){
            return new SearchDTO(query, fromTime, fromDate, fromMagnitude, toTime, toDate, toMagnitude);
        }

        void setQuery(String query) {
            this.query = query;
        }

        void setFromTime(String fromTime) {
            this.fromTime = fromTime;
        }

        void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        void setToTime(String toTime) {
            this.toTime = toTime;
        }

        void setToDate(String toDate) {
            this.toDate = toDate;
        }

        void setFromMagnitude(String fromMagnitude) {
            this.fromMagnitude = fromMagnitude;
        }

        void setToMagnitude(String toMagnitude) {
            this.toMagnitude = toMagnitude;
        }
    }
}

package com.ai.util.time;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by eason on 2017/2/16.
 */
public class TimeUtils {
    private static final Logger log = LoggerFactory.getLogger(TimeUtils.class);
    public static Date getEndDateWithDays(int day){
        DateTime dateTime = new DateTime();
        return dateTime.plusDays(day).millisOfDay().withMinimumValue().toDate();
    }

   /* public static void main(String[] args) {
        log.error(""+TimeUtils.getEndDateWithDays(5).getTime());
    }*/
}

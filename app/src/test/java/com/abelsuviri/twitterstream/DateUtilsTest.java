package com.abelsuviri.twitterstream;

import com.abelsuviri.twitterstream.utils.DateUtils;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class contains the test for all methods of {@link DateUtils} class.
 *
 * @author Abel Suviri
 */

public class DateUtilsTest {

    @Test
    public void getDateTest() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String dateString = "05/03/2017";
            Date date = simpleDateFormat.parse(dateString);

            Assert.assertEquals(dateString, DateUtils.getDate(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTimeTest() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            String timeString = "12:00";
            Date time = simpleDateFormat.parse(timeString);

            Assert.assertEquals(timeString, DateUtils.getTime(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTweetDateTest() {
        Assert.assertEquals("04/03/2017", DateUtils.getTweetDate("Sat Mar 04 19:23:15 +0000 2017"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy",
            Locale.ENGLISH);
        String date = simpleDateFormat.format(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        String time = sdf.format(new Date());

        Assert.assertEquals(time, DateUtils.getTweetDate(date));
    }
}

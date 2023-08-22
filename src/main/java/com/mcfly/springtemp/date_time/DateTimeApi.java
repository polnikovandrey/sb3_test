package com.mcfly.springtemp.date_time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateTimeApi {

    public static void main(String... args) {
        /*
         Java8 DateTimeApi (java.time package) classes:
            Clock
            Instant
            Duration
            Period
            LocalDate, LocalTime, LocalDateTime, OffsetDateTime, ZonedDateTime
            TemporalAdjusters, DayOfWeek, Month, Year
            ZoneId, ZoneOffset
            DateTimeFormatter, FormatStyle
         */
        localDate();
        localTime();
        localDateTime();
        localDate_TimeMethods();
        yearMethods();
        timeZones();
        timestamps();
        periodsAndDurations();
        formattingAndParsing();
        conversions();
    }

    private static void localDate() {
        // the current date
        LocalDate currentDate = LocalDate.now();
        // 2014-02-10
        LocalDate tenthFeb2014 = LocalDate.of(2014, Month.FEBRUARY, 10);
        // months values start at 1 (2014-08-01)
        LocalDate firstAug2014 = LocalDate.of(2014, 8, 1);
        // the 65th day of 2010 (2010-03-06)
        LocalDate sixtyFifthDayOf2010 = LocalDate.ofYearDay(2010, 65);
    }

    private static void localTime() {
        LocalTime currentTime = LocalTime.now(); // current time
        LocalTime midday = LocalTime.of(12, 0); // 12:00
        LocalTime afterMidday = LocalTime.of(13, 30, 15); // 13:30:15
        // 12345th second of day (03:25:45)
        LocalTime fromSecondsOfDay = LocalTime.ofSecondOfDay(12345);
        // current (local) time in Los Angeles
        LocalTime currentTimeInLosAngeles = LocalTime.now(ZoneId.of("America/Los_Angeles"));
        // current time in UTC time zone
        LocalTime nowInUtc = LocalTime.now(Clock.systemUTC());
    }

    private static void localDateTime() {
        // dates with times, e.g. 2014-02-18 19:08:37.950
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 2014-10-02 12:30
        LocalDateTime secondAug2014 = LocalDateTime.of(2014, 10, 2, 12, 30);
        // 2014-12-24 12:00
        LocalDateTime christmas2014 = LocalDateTime.of(2014, Month.DECEMBER, 24, 12, 0);
    }

    private static void localDate_TimeMethods() {
        LocalDate date = LocalDate.of(2014, 2, 15); // 2014-06-15
        boolean isBefore = LocalDate.now().isBefore(date); // false
        // information about the month
        Month february = date.getMonth(); // FEBRUARY
        int februaryIntValue = february.getValue(); // 2
        int minLength = february.minLength(); // 28
        int maxLength = february.maxLength(); // 29
        Month firstMonthOfQuarter = february.firstMonthOfQuarter(); // JANUARY
        // information about the year
        int year = date.getYear(); // 2014
        int dayOfYear = date.getDayOfYear(); // 46
        int lengthOfYear = date.lengthOfYear(); // 365
        boolean isLeapYear = date.isLeapYear(); // false
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfWeekIntValue = dayOfWeek.getValue(); // 6
        String dayOfWeekName = dayOfWeek.name(); // SATURDAY
        int dayOfMonth = date.getDayOfMonth(); // 15
        LocalDateTime startOfDay = date.atStartOfDay(); // 2014-02-15 00:00
        // time information
        LocalTime time = LocalTime.of(15, 30); // 15:30:00
        int hour = time.getHour(); // 15
        int second = time.getSecond(); // 0
        int minute = time.getMinute(); // 30
        int secondOfDay = time.toSecondOfDay(); // 55800
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        // before 5 hours and 30 minutes
        LocalDateTime dateTime = LocalDateTime.now().minusHours(5).minusMinutes(30);
        LocalDate date1 = LocalDate.of(2014, Month.FEBRUARY, 25); // 2014-02-25
        // first day of february 2014 (2014-02-01)
        LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        // last day of february 2014 (2014-02-28)
        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
    }

    private static void yearMethods() {
        Year currentYear = Year.now();
        Year twoThousand = Year.of(2000);
        boolean isLeap = currentYear.isLeap(); // false
        int length = currentYear.length(); // 365
        // sixtyFourth day of 2014 (2014-03-05)
        LocalDate date2 = Year.of(2014).atDay(64);
    }

    private static void timeZones() {
        ZoneId losAngeles = ZoneId.of("America/Los_Angeles");
        ZoneId berlin = ZoneId.of("Europe/Berlin");
        // 2014-02-20 12:00
        LocalDateTime dateTime1 = LocalDateTime.of(2014, 2, 20, 12, 0);
        // 2014-02-20 12:00, Europe/Berlin (+01:00)
        ZonedDateTime berlinDateTime = ZonedDateTime.of(dateTime1, berlin);
        // 2014-02-20 03:00, America/Los_Angeles (-08:00)
        ZonedDateTime losAngelesDateTime = berlinDateTime.withZoneSameInstant(losAngeles);
        int offsetInSeconds = losAngelesDateTime.getOffset().getTotalSeconds(); // -28800
        // a collection of all available zones
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        // using offsets
        LocalDateTime date3 = LocalDateTime.of(2013, Month.JULY, 20, 3, 30);
        ZoneOffset offset = ZoneOffset.of("+05:00");
        // 2013-07-20 03:30 +05:00
        OffsetDateTime plusFive = OffsetDateTime.of(date3, offset);
        // 2013-07-19 20:30 -02:00
        OffsetDateTime minusTwo = plusFive.withOffsetSameInstant(ZoneOffset.ofHours(-2));
    }

    private static void timestamps() {
        // current time
        Instant now = Instant.now();
        // from unix timestamp, 2010-01-01 12:00:00
        Instant fromUnixTimestamp = Instant.ofEpochSecond(1262347200);
        // same time in millis
        Instant fromEpochMilli = Instant.ofEpochMilli(1262347200000L);
        // parsing from ISO 8601
        Instant fromIso8601 = Instant.parse("2010-01-01T12:00:00Z");
        // toString() returns ISO 8601 format, e.g. 2014-02-15T01:02:03Z
        String toIso8601 = now.toString();
        // as unix timestamp
        long toUnixTimestamp = now.getEpochSecond();
        // in millis
        long toEpochMillis = now.toEpochMilli();
        // plus/minus methods are available too
        Instant nowPlusTenSeconds = now.plusSeconds(10);
    }

    private static void periodsAndDurations() {
        // periods
        LocalDate firstDate = LocalDate.of(2010, 5, 17); // 2010-05-17
        LocalDate secondDate = LocalDate.of(2015, 3, 7); // 2015-03-07
        Period period = Period.between(firstDate, secondDate);
        int days = period.getDays(); // 18
        int months = period.getMonths(); // 9
        int years = period.getYears(); // 4
        boolean isNegative = period.isNegative(); // false
        Period twoMonthsAndFiveDays = Period.ofMonths(2).plusDays(5);
        LocalDate sixthOfJanuary = LocalDate.of(2014, 1, 6);
        // add two months and five days to 2014-01-06, result is 2014-03-11
        LocalDate eleventhOfMarch = sixthOfJanuary.plus(twoMonthsAndFiveDays);
        // durations
        Instant firstInstant= Instant.ofEpochSecond( 1294881180 ); // 2011-01-13 01:13
        Instant secondInstant = Instant.ofEpochSecond(1294708260); // 2011-01-11 01:11
        Duration between = Duration.between(firstInstant, secondInstant);
        // negative because firstInstant is after secondInstant (-172920)
        long seconds = between.getSeconds();
        // get absolute result in minutes (2882)
        long absoluteResult = between.abs().toMinutes();
        // two hours in seconds (7200)
        long twoHoursInSeconds = Duration.ofHours(2).getSeconds();
    }

    private static void formattingAndParsing() {
        // 2014-04-01 10:45
        LocalDateTime dateTime2 = LocalDateTime.of(2014, Month.APRIL, 1, 10, 45);
        // format as basic ISO date format (20140220)
        String asBasicIsoDate = dateTime2.format(DateTimeFormatter.BASIC_ISO_DATE);
        // format as ISO week date (2014-W08-4)
        String asIsoWeekDate = dateTime2.format(DateTimeFormatter.ISO_WEEK_DATE);
        // format ISO date time (2014-02-20T20:04:05.867)
        String asIsoDateTime = dateTime2.format(DateTimeFormatter.ISO_DATE_TIME);
        // using a custom pattern (01/04/2014)
        String asCustomPattern = dateTime2.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        // french date formatting (1. avril 2014)
        String frenchDate = dateTime2.format(DateTimeFormatter.ofPattern("d. MMMM yyyy", new Locale("fr")));
        // using short german date/time formatting (01.04.14 10:45)
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(new Locale("de"));
        String germanDateTime = dateTime2.format(formatter);
        // parsing date strings
        LocalDate fromIsoDate = LocalDate.parse("2014-01-20");
        LocalDate fromIsoWeekDate = LocalDate.parse("2014-W14-2", DateTimeFormatter.ISO_WEEK_DATE);
        LocalDate fromCustomPattern = LocalDate.parse("20.01.2014", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static void conversions() {
        // LocalDate/LocalTime <-> LocalDateTime
        LocalDate date4 = LocalDate.now();
        LocalTime time1 = LocalTime.now();
        LocalDateTime dateTimeFromDateAndTime = LocalDateTime.of(date4, time1);
        LocalDate dateFromDateTime = LocalDateTime.now().toLocalDate();
        LocalTime timeFromDateTime = LocalDateTime.now().toLocalTime();
        // Instant <-> LocalDateTime
        Instant instant = Instant.now();
        LocalDateTime dateTimeFromInstant = LocalDateTime.ofInstant(instant, ZoneId.of("America/Los_Angeles"));
        Instant instantFromDateTime = LocalDateTime.now().toInstant(ZoneOffset.ofHours(-2));
        // convert old date/calendar/timezone classes
        Instant instantFromDate = new Date().toInstant();
        Instant instantFromCalendar = Calendar.getInstance().toInstant();
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        ZonedDateTime zonedDateTimeFromGregorianCalendar = new GregorianCalendar().toZonedDateTime();
        // convert to old classes
        Date dateFromInstant = Date.from(Instant.now());
        TimeZone timeZone = TimeZone.getTimeZone(ZoneId.of("America/Los_Angeles"));
        GregorianCalendar gregorianCalendar = GregorianCalendar.from(ZonedDateTime.now());
    }
}

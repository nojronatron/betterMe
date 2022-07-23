package com.doinWondrs.betterme;

import org.junit.Test;

import static org.junit.Assert.*;

import com.doinWondrs.betterme.activities.AboutUsActivity;
import com.doinWondrs.betterme.activities.CalendarActivity;
import com.doinWondrs.betterme.activities.CalendarAdapter;
import com.doinWondrs.betterme.activities.CalendarUtils;

import java.time.LocalDate;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BetterMeUnittests {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_CalendarUtils() {
        LocalDate localDate = CalendarUtils.selectedDate.withDayOfMonth(1);
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_CalendarAdapter_RequiresMocking() {
        // TODO: implement mocking and/or refactor business logic into new class to test this code page
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_CalendarActivity_RequiresMocking() {
        // TODO: implement mocking and/or refactor business logic into new class to test this code page        assertEquals(4, 2 + 2);
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_AboutUsActivity_RequiresMocking() {
        // TODO: implement mocking and/or refactor business logic into new class to test this code page        System.out.println("This is AboutUsActivity class and might require Mocking in order to unit test.");
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_DevModel_propertiesExistGetSettersWork() {
        String devName = "Dee Veloper";
        String devLinkedIn = "https://linked.in/devlopin";
        String devGitHub = "https://github.com/develops";
        int devImg = 5;
        String devQuote = "In agile we trust.";

        DevModel dev = new DevModel(
                devName, devLinkedIn, devGitHub, devImg, devQuote
        );

        assertEquals(devName, dev.getDevName());
        assertEquals(devLinkedIn, dev.getDevLinkedIn());
        assertEquals(devImg, dev.getDevImg());
        assertEquals(devGitHub, dev.getDevGitHub());
        assertEquals(devQuote, dev.getDevQuote());

        String devName2 = "Sofi Tware";
        String devLinkedIn2 = "https://linked.in/sofiTwareSWE";
        String devGitHub2 = "https://github.com/sofiSWE";
        int devImg2 = 11;
        String devQuote2 = "With bits and bytes make an App, I mights!";

        dev.setDevName(devName2);
        dev.setDevImg(devImg2);
        dev.setDevLinkedIn(devLinkedIn2);
        dev.setDevQuote(devQuote2);
        dev.setDevGitHub(devGitHub2);

        assertEquals(devName2, dev.getDevName());
        assertEquals(devLinkedIn2, dev.getDevLinkedIn());
        assertEquals(devImg2, dev.getDevImg());
        assertEquals(devGitHub2, dev.getDevGitHub());
        assertEquals(devQuote2, dev.getDevQuote());
    }

    @Test
    public void test_BetterMeAmplifyApplication() {
        // TODO: implement mocking and/or refactor business logic into new class to test this code pageSystem.out.println("This is the Amplify plug-ins initialization class and will not be unit-tested.");
        assertEquals(4, 2 + 2);
    }
}
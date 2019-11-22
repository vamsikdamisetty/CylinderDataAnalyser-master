package com.example.dhawal.cylinderdataanalyser;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SignupActivity
{
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<SignUp>(
            SignUp.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.dhawal.cylinderdataanalyser",
                appContext.getPackageName());


    }
}

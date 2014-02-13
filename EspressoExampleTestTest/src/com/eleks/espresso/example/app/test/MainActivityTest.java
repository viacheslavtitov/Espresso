package com.eleks.espresso.example.app.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.mockito.Mockito;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.eleks.espresso.example.app.HttpTransport;
import com.eleks.espresso.example.app.MainActivity;
import com.eleks.espresso.example.app.WebServiceManager;
import com.eleks.espresso.example.app.WebServiceManager.Builder;
import com.google.android.apps.common.testing.ui.espresso.Espresso;
import com.google.android.apps.common.testing.ui.espresso.action.ViewActions;
import com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;
import com.google.common.base.Preconditions;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	public MainActivityTest()
	{
		super(MainActivity.class);
	}

	public MainActivityTest(Class<MainActivity> activityClass)
	{
		super(activityClass);
	}

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		getActivity();
	}

	@Override
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	@MediumTest
	public void testSendMessageTransport() throws Exception
	{
		String email = "test@email.com";
		String message = "something";
		HttpTransport mockHttpTransport = Mockito.mock(HttpTransport.class);
		Builder builder = Builder.getInstance();
		builder.setHttpTransport(mockHttpTransport);
		WebServiceManager.getInstance().setBuilder(builder);
		Mockito.when(mockHttpTransport.execute("")).thenReturn("");
		WebServiceManager.getInstance().sendMessage(email, message);
		Mockito.verify(mockHttpTransport).execute("");
	}

	@MediumTest
	public void testSendMessageUI()
	{
		Espresso.onView(ViewMatchers.withId(com.eleks.espresso.example.app.R.id.etEmail)).perform(ViewActions.typeText("test@test.com"));
		Espresso.onView(ViewMatchers.withId(com.eleks.espresso.example.app.R.id.etMessage)).perform(ViewActions.typeText("Espresso"));
		Espresso.onView(ViewMatchers.withId(com.eleks.espresso.example.app.R.id.btnSend)).perform(ViewActions.click());
	}

	@SuppressWarnings("unchecked")
	@SmallTest
	public void testOpenNavigationDrawer()
	{
		Espresso.onView(ViewMatchers.withId(com.eleks.espresso.example.app.R.id.content_frame)).perform(ViewActions.swipeRight());
		ListView lvDrawerMenu = (ListView) getActivity().findViewById(com.eleks.espresso.example.app.R.id.lvDrawerMenu);
		Preconditions.checkNotNull(lvDrawerMenu, "lvDrawerMenu is null");
		final int count = lvDrawerMenu.getAdapter().getCount();
		Preconditions.checkPositionIndex(2, count, "No 1 index " + count + " size");
		Object obj = lvDrawerMenu.getItemAtPosition(2);
		Espresso.onView(Matchers.allOf(ViewMatchers.withId(com.eleks.espresso.example.app.R.id.tvItem), ViewMatchers.hasSibling(ViewMatchers.withText(obj.toString())))).perform(ViewActions.click());
	}

}

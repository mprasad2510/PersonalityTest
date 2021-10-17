package com.example.personalitytestapp


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.personalitytestapp.view.ui.QuestionListActivity
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf


import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class QuestionListFeatureInstrumentedTest {
    val mActivityRule = ActivityTestRule(QuestionListActivity::class.java)
    @Rule get

    @Test
    fun displayScreenTitle() {
        assertDisplayed("PersonalityTestApp")
    }

    @Test
    fun displayListOfQuestions() {
        Thread.sleep(4000)

      assertRecyclerViewItemCount(R.id.list_of_questions, 25)
        onView(allOf(withId(R.id.list_of_questions), isDescendantOfA(nthChildOf(withId(R.id.category),0))))
            .check(matches(withText("hard_fact")))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.list_of_questions), isDescendantOfA(nthChildOf(withId(R.id.question),0))))
            .check(matches(withText("What is your gender")))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.list_of_questions), isDescendantOfA(nthChildOf(withId(R.id.option1),0))))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.list_of_questions), isDescendantOfA(nthChildOf(withId(R.id.option2),0))))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.list_of_questions), isDescendantOfA(nthChildOf(withId(R.id.option3),0))))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.list_of_questions), isDescendantOfA(nthChildOf(withId(R.id.option4),0))))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.list_of_questions), isDescendantOfA(nthChildOf(withId(R.id.option5),0))))
            .check(matches(isDisplayed()))
    }

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of Parent")
                parentMatcher.describeTo(description)
            }

            override fun matchesSafely(item: View): Boolean {
                if (item.parent !is ViewGroup) return false
                val parent = item.parent as ViewGroup
                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == item)
            }
        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.personalitytestapp", appContext.packageName)
    }
}
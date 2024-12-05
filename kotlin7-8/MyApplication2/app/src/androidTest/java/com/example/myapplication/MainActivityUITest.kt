package com.example.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testUrlInputFieldIsDisplayed() {
        onView(withId(R.id.urlInput))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoadButtonIsDisplayed() {
        onView(withId(R.id.button))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoadButtonFunctionality() {
        onView(withId(R.id.urlInput))
            .perform(typeText("https://funik.ru/wp-content/uploads/2018/10/17478da42271207e1d86.jpg"), closeSoftKeyboard())
        onView(withId(R.id.button))
            .perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.imageView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testEmptyUrlDoesNotCrash() {
        onView(withId(R.id.urlInput))
            .perform(clearText()) // Убедитесь, что поле ввода пустое
        onView(withId(R.id.button))
            .perform(click())

        onView(withId(R.id.urlInput)).check(matches(isDisplayed()))
        onView(withId(R.id.button)).check(matches(isDisplayed()))
    }

    @Test
    fun testImageVisibilityAfterValidUrl() {
        onView(withId(R.id.urlInput))
            .perform(typeText("https://funik.ru/wp-content/uploads/2018/10/17478da42271207e1d86.jpg"), closeSoftKeyboard())
        onView(withId(R.id.button))
            .perform(click())
        Thread.sleep(3000) // Ожидание загрузки изображения
        onView(withId(R.id.imageView))
            .check(matches(isDisplayed()))
    }
}

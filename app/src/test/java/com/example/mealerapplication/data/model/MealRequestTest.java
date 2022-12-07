package com.example.mealerapplication.data.model;

import static org.junit.Assert.*;

import com.google.common.truth.Truth;

import org.junit.Test;

import java.util.Map;

public class MealRequestTest {

    @Test
    public void getMealName() {
        MealRequest request = new MealRequest("clientIDTest", "cookIDTest", "mealIDTest",
                "Test meal", "Test Client", "complete", "Test Cook", "75 Laurier St E");
        Truth.assertThat(request.getMealName()).isEqualTo("Test meal");
    }

    @Test
    public void getStatus() {
        MealRequest request = new MealRequest("clientIDTest", "cookIDTest", "mealIDTest",
                "Test meal", "Test Client", "complete", "Test Cook", "75 Laurier St E");
        Truth.assertThat(request.getStatus()).isEqualTo("complete");
    }

    @Test
    public void setMealName() {
        MealRequest request = new MealRequest("clientIDTest", "cookIDTest", "mealIDTest",
                "Test meal", "Test Client", "complete", "Test Cook", "75 Laurier St E");
        request.setMealName("Different meal name");
        Truth.assertThat(request.getMealName()).isEqualTo("Different meal name");
    }

    @Test
    public void getMealRequestMap() {
        MealRequest request = new MealRequest("clientIDTest", "cookIDTest", "mealIDTest",
                "Test meal", "Test Client", "complete", "Test Cook", "75 Laurier St E");
        Map<String, Object> map = request.getMealRequestMap();
        Truth.assertThat(map).containsEntry("clientID", "clientIDTest");
        Truth.assertThat(map).containsEntry("cookID", "cookIDTest");
        Truth.assertThat(map).containsEntry("mealID", "mealIDTest");
        Truth.assertThat(map).containsEntry("mealName", "Test meal");
        Truth.assertThat(map).containsEntry("clientName", "Test Client");
        Truth.assertThat(map).containsEntry("status", "complete");
        Truth.assertThat(map).containsEntry("cookName", "Test Cook");
        Truth.assertThat(map).containsEntry("clientAddress", "75 Laurier St E");
    }
}
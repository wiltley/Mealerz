package com.example.mealerapplication.data.model;

import static org.junit.Assert.*;
import com.google.common.truth.Truth;
import java.util.Map;

import org.junit.Test;

public class RecipeTest {

    @Test
    public void getRecipeMap() {
        Recipe recipe = new Recipe("Pizza", "Classic cheese pizza", "Gordon Ramsay",
                "testDocumentID", "testCookID", "Italian", "true", "12");
        Map<String, Object> map = recipe.getRecipeMap();
        Truth.assertThat(map).containsEntry("recipeName", "Pizza");
        Truth.assertThat(map).containsEntry("description", "Classic cheese pizza");
        Truth.assertThat(map).containsEntry("cookName", "Gordon Ramsay");
        Truth.assertThat(map).containsEntry("cookID", "testCookID");
        Truth.assertThat(map).containsEntry("cuisineType", "Italian");
        Truth.assertThat(map).containsEntry("offered", "Offered");
        Truth.assertThat(map).containsEntry("price", "12");
    }

    @Test
    public void testToString() {
        Recipe recipe = new Recipe("Pizza", "Classic cheese pizza", "Gordon Ramsay",
                "testDocumentID", "testCookID", "Italian", "true", "12");
        Truth.assertThat(recipe.toString()).isEqualTo("Pizza by Gordon Ramsay ($12): Classic cheese pizza \n Cuisine Type: Italian");
    }
}
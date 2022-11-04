package com.example.mealerapplication.data;

import static org.junit.Assert.*;
import com.google.common.truth.Truth;

import org.junit.Test;

import java.util.Map;

public class UserTest {

    @Test
    public void getEmail() {
    }

    @Test
    public void isClient() {
        User user = new User("testuser", "test@email.com", User.Role.CLIENT);
        Truth.assertThat(user.getRole().getRole()).isEqualTo(0);
        Truth.assertThat(user.getRole().getRoleString()).isEqualTo("client");
        Truth.assertThat(user.isClient()).isTrue();
        Truth.assertThat(user.isCook()).isFalse();
        Truth.assertThat(user.isAdmin()).isFalse();
    }

    @Test
    public void isCook() {
        User user = new User("testuser", "test@email.com", User.Role.COOK);
        Truth.assertThat(user.getRole().getRole()).isEqualTo(1);
        Truth.assertThat(user.getRole().getRoleString()).isEqualTo("cook");
        Truth.assertThat(user.isClient()).isFalse();
        Truth.assertThat(user.isCook()).isTrue();
        Truth.assertThat(user.isAdmin()).isFalse();
    }

    @Test
    public void isAdmin() {
        User user = new User("testuser", "test@email.com", User.Role.ADMIN);
        Truth.assertThat(user.getRole().getRole()).isEqualTo(2);
        Truth.assertThat(user.getRole().getRoleString()).isEqualTo("admin");
        Truth.assertThat(user.isClient()).isFalse();
        Truth.assertThat(user.isCook()).isFalse();
        Truth.assertThat(user.isAdmin()).isTrue();
    }

    @Test
    public void getUserMap() {
        User user = new User("testuser", "test@email.com", User.Role.CLIENT);
        user.setfName("John");
        user.setlName("Doe");
        Map<String, Object> map = user.getUserMap();
        Truth.assertThat(map).containsEntry("username", "testuser");
        Truth.assertThat(map).containsEntry("email", "test@email.com");
        Truth.assertThat(map).containsEntry("fName", "John");
        Truth.assertThat(map).containsEntry("lName", "Doe");
        Truth.assertThat(map).containsEntry("role", 0);

    }
}
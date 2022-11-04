package com.example.mealerapplication.data.model;

import static org.junit.Assert.*;

import com.google.common.truth.Truth;

import org.junit.Test;

import java.util.Map;

public class AddressTest {

    @Test
    public void testToString() {
        Address address = new Address(123, "Sesame St", "New York", "NY", "USA", "10005");
        Truth.assertThat(address.toString()).matches("123 Sesame St, New York, NY, USA, 10005");
        address = new Address(100, "Queen St W", "Ottawa", "ON", "Canada", "K1P 1J9");
        Truth.assertThat(address.toString()).matches("100 Queen St W, Ottawa, ON, Canada, K1P 1J9");
    }

    @Test
    public void testGetAddressMap() {
        Address address = new Address(100, "Queen St W", "Ottawa", "ON", "Canada", "K1P 1J9");
        Map<String, Object> map = address.getAddressMap();
        Truth.assertThat(map).containsEntry("houseNumber", 100);
        Truth.assertThat(map).containsEntry("streetName", "Queen St W");
        Truth.assertThat(map).containsEntry("city", "Ottawa");
        Truth.assertThat(map).containsEntry("province", "ON");
        Truth.assertThat(map).containsEntry("country", "Canada");
        Truth.assertThat(map).containsEntry("postalCode", "K1P 1J9");
    }

    @Test
    public void testEquals() {
        Address address1 = new Address(100, "Queen St W", "Ottawa", "ON", "Canada", "K1P 1J9");
        Address address2 = new Address(100, "Queen St W", "Ottawa", "ON", "Canada", "K1P 1J9");
        Truth.assertThat(address1.equals(address2)).isTrue();
        address2.setStreetName("Queen St");
        Truth.assertThat(address1.equals(address2)).isFalse();
        address2.setStreetName("Queen St W");
        address2.setProvince("AB");
        Truth.assertThat(address1.equals(address2)).isFalse();
    }
}
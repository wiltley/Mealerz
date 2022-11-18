package com.example.mealerapplication.data.model;

import static org.junit.Assert.*;
import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.Map;

public class ComplaintTest {

    @Test
    public void getComplaintMap() {
        Complaint complaint =  new Complaint("test accuser", "test accused", "generic complaint message",
                "document id", "accusedUID");
        Map<String, Object> map = complaint.getComplaintMap();
        Truth.assertThat(map).containsEntry("accuser", "test accuser");
        Truth.assertThat(map).containsEntry("accused", "test accused");
        Truth.assertThat(map).containsEntry("message", "generic complaint message");
        Truth.assertThat(map).containsEntry("accusedUID", "accusedUID");
    }


    @Test
    public void testToString() {
        Complaint complaint =  new Complaint("test accuser", "test accused", "generic complaint message",
                "document id", "accusedUID");
        Truth.assertThat(complaint.toString()).isEqualTo("Complaint by test accuser against test accused: generic complaint message");
    }
}
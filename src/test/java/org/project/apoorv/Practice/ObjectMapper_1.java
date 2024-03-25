package org.project.apoorv.Practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class ObjectMapper_1 {
    @Test
    public void createBooking() throws JsonProcessingException {

        //Create ObjectMapper class instance
        ObjectMapper objectmapper = new ObjectMapper();

        //Create Object node i.e., JSON node
        ObjectNode userDetails = objectmapper.createObjectNode();
        userDetails.put("firstname","Apoorv");
        userDetails.put("lastname","Jain");
        userDetails.put("age",28);
        userDetails.put("salary",10000.56);
        userDetails.put("isMarried",true);
        ObjectNode userDetails1 = objectmapper.createObjectNode();
        userDetails1.put("Programming","java");
        userDetails.set("TechSkill",userDetails1);

        String abc = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDetails);
        System.out.println(abc);

        //print firstname value
        System.out.println(userDetails.get("firstname").asText());
        System.out.println(userDetails.get("TechSkill").get("Programming").asText());
    }
}

package com.nashss.se.mctracker.dependency;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CustomListDeserializer extends JsonDeserializer<List<String>> {
    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        if (p.getCurrentToken() == JsonToken.VALUE_STRING) {
            String value = p.getValueAsString();
            List<String> list = new ArrayList<>();
            list.add(value);
            return list;
        } else {
            return p.readValueAs(ArrayList.class);
        }
    }
}

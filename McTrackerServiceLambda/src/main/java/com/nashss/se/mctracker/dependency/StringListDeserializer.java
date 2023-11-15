package com.nashss.se.mctracker.dependency;

import com.fasterxml.jackson.core.JsonParser;

import com.fasterxml.jackson.databind.DeserializationContext;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.util.Arrays;

    public class StringListDeserializer extends StdDeserializer<List<String>> {

        public StringListDeserializer() {
            this(null);
        }

        public StringListDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public List<String> deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            JsonNode node = jp.getCodec().readTree(jp);
            String[] items = node.asText().split(",");
            return Arrays.asList(items);
        }
    }


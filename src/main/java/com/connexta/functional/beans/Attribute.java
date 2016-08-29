package com.connexta.functional.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Attribute {
    private final AttributeType type;

    private final boolean isMulti;

    private final String name;

    private ArrayList<Serializable> values = new ArrayList<>();

    public Attribute(AttributeType type, boolean isMulti, String name) {
        this.type = type;
        this.isMulti = isMulti;
        this.name = name;
    }

    public AttributeType getType() {
        return type;
    }

    public boolean isMulti() {
        return isMulti;
    }

    public String getName() {
        return name;
    }

    public Serializable getValue() {
        if (values.isEmpty()) {
            return null;
        }

        if (isMulti) {
            return new ArrayList<>(values);
        } else {
            return values.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    public void setValue(Serializable value) {
        if (isMulti) {
            values = new ArrayList<>((ArrayList) value);
        } else {
            values = new ArrayList<>();
            values.add(value);
        }
    }
}

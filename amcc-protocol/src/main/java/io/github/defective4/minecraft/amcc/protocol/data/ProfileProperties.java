package io.github.defective4.minecraft.amcc.protocol.data;

import java.util.HashMap;

import com.google.gson.Gson;

public class ProfileProperties extends HashMap<String, String> {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

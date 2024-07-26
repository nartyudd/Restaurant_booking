package com.example.booking_restaurant.utilities;

import java.util.UUID;

public class GenID {
    public static String genUUID() {
        return UUID.randomUUID().toString();
    }
}

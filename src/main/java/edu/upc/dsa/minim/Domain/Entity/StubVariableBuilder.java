package edu.upc.dsa.minim.Domain.Entity;

import net.moznion.random.string.RandomStringGenerator;

public class StubVariableBuilder {
    public static String randomId(){
        RandomStringGenerator generator = new RandomStringGenerator();
        String randomString = generator.generateByRegex("\\w+\\d*[0-9]{0,8}");

        return randomString;
    }
}

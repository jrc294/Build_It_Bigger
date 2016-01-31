package com.jonathan.learning;

import java.util.Random;

public class JokeEngine {

    String[] jokes = new String[]{"Why did the chicken cross the road? To get to the other side",
                                    "Anything that is unrelated to elephants is irrelaphant",
                                    "I feel sorry for shopping carts, they are always getting pushed around!",
                                    "What do you call a magic owl? Hoodini",
                                    "How do asteroids get so big? They take A-Steroid!",
                                    "Why don't you ever see hippopotamus hiding in trees? Because they're really good at it.",
                                    "Did you hear about the nun who quit? . . . she kicked her \"Habit\"!",
                                    "What's white and can't climb a tree? A Fridge",
                                    "What's green, brown and white and can't climb a tree? A Fridge in a combat jacket",
                                    "What is Dr. Jekyll when he is himself? De-hyde-rated!"};

    public String getJoke() {

        Random rnd = new Random();
        int ix = rnd.nextInt(jokes.length);
        return jokes[ix];
    }
}

package edu.dougwoodrow.academy.services;

import edu.dougwoodrow.academy.entities.mongodb.Test;

import java.util.HashMap;

public interface TestingService {

    HashMap<String, Boolean> run(HashMap<String, ? extends Test> testCases);
}

package edu.dougwoodrow.academy.services;

import edu.dougwoodrow.academy.entities.mongodb.JavaTest;
import edu.dougwoodrow.academy.entities.mongodb.Test;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;

@Service
public class JavaTestingService implements TestingService {

    @Inject
    private ShellService shellService;

    @Override
    public HashMap<String, Boolean> run(HashMap<String, ? extends Test> testCases) {
        HashMap<String, Boolean> results = new HashMap<>();

        for(String testCase : testCases.keySet()) {
            JavaTest test = new JavaTest();
            test.setClassName("JavaTestingTest");

            Boolean result = this.test(test);

            results.put(testCase, result);
        }

        return results;
    }

    private Boolean test(JavaTest test) {
        return this.shellService.execute("gradle test --tests " + test.getClassName());
    }
}

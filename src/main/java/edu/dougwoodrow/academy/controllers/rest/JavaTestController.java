package edu.dougwoodrow.academy.controllers.rest;

import edu.dougwoodrow.academy.entities.mongodb.JavaTest;
import edu.dougwoodrow.academy.services.JavaTestingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashMap;

@RestController
public class JavaTestController {

    @Inject
    private JavaTestingService javaTestingService;

    @GetMapping(path = "/api/test/java/run")
    public ResponseEntity<?> run() {
        HashMap<String, JavaTest> javaTestCases = new HashMap<>();

        JavaTest javaTest = new JavaTest();
        javaTest.setClassName("JavaTestingTest");
        javaTestCases.put("Tests the health of the testing feature", javaTest);

        return new ResponseEntity<>(this.javaTestingService.run(javaTestCases), HttpStatus.OK);
    }
}

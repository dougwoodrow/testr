package edu.dougwoodrow.academy.services;

import edu.dougwoodrow.academy.utilities.StreamHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Executors;

@Service
public class ShellService {

    public Boolean execute(String script) {
        Process process;

        try {
            process = Runtime.getRuntime().exec(String.format("sh -c " + script));

            StreamHandler streamHandler = new StreamHandler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamHandler);
            int exitCode = process.waitFor();

            return exitCode == 0;
        } catch (IOException ioe) {
            return false;
        } catch (InterruptedException ie) {
            return false;
        }
    }

}

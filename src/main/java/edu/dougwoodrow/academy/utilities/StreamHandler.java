package edu.dougwoodrow.academy.utilities;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class StreamHandler implements Runnable {

    private InputStream inputStream;

    private Consumer<String> consumer;

    public StreamHandler(InputStream inputStream, Consumer<String> consumer) {
        this.inputStream = inputStream;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("sh", "-c", "ls");
            builder.directory(new File(System.getProperty("user.home")));
            Process process = builder.start();

            StreamHandler streamHandler = new StreamHandler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamHandler);

            int exitCode = process.waitFor();
            assert exitCode == 0;
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        } catch (IOException ioe) {

        } catch (InterruptedException ie) {

        }

    }
}

package edu.wgu.d387_sample_code.convertor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WelcomeService {

    public Map<String, String> loadWelcomeMessage() {
        Map<String, String> messages = new ConcurrentHashMap<>();
        List<Thread> threads = new ArrayList<>();

        Runnable englishWelcome = () -> {
            try (InputStream stream = new ClassPathResource("translation_en.properties").getInputStream()) {
                Properties properties = new Properties();
                properties.load(stream);
                messages.put("en", properties.getProperty("welcome"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable frenchWelcome = () -> {
            try (InputStream stream = new ClassPathResource("translation_fr.properties").getInputStream()) {
                Properties properties = new Properties();
                properties.load(stream);
                messages.put("fr", properties.getProperty("welcome"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        threads.add(new Thread(englishWelcome));
        threads.add(new Thread(frenchWelcome));

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException ignored) {}
        });

        return messages;
    }
}

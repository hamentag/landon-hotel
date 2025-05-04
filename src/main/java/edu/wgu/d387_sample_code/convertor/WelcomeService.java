package edu.wgu.d387_sample_code.convertor;

import edu.wgu.d387_sample_code.model.response.WelcomeResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WelcomeService {
    public WelcomeResponse loadWelcomeMessage() {
        Map<String, String> messages = new ConcurrentHashMap<>();
        List<Thread>  threads = new ArrayList<>();

        Runnable englishWelcome = () -> {
            try {
                Properties properties = new Properties();
                InputStream stream = new ClassPathResource("translation_en.properties").getInputStream();
                properties.load(stream);
                messages.put("en", properties.getProperty("welcome"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable frenchWelcome = () -> {
            try {
                Properties properties = new Properties();
                InputStream stream = new ClassPathResource("translation_fr.properties").getInputStream();
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

        return new WelcomeResponse(messages);
    }
}

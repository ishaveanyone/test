package com.dist.liscence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;

@Configuration
public class RegisterConfig {
    private static Logger logger = LoggerFactory.getLogger(RegisterConfig.class);

    @Bean(name="liscenceDate")
    public Date liscenceDate() {
        Date liscenceDate = null;

        try (InputStream liscenceStream = RegisterConfig.class.getClassLoader().getResourceAsStream("liscence");
             ObjectInputStream in = new ObjectInputStream(liscenceStream)
        ) {
            Object obj = in.readObject();
            if (obj instanceof Date) {
                liscenceDate = (Date) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (liscenceDate == null) {
            logger.error("liscence load error");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+7);
            liscenceDate = calendar.getTime();
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                    RegisterConfig.class.getClassLoader().getResource("").getPath()
                            +"liscence"))) {
            out.writeObject(liscenceDate);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("liscence create error");
        }
    }

        return liscenceDate;
    }


}

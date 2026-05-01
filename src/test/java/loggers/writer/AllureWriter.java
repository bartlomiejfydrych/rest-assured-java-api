package loggers.writer;

import io.qameta.allure.Allure;
import loggers.formatter.AllureAttachment;

public class AllureWriter {

    public void write(AllureAttachment attachment) {
        Allure.addAttachment(
                attachment.title,
                "application/json",
                attachment.content,
                ".json"
        );
    }
}

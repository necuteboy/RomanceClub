package org.example;

import org.example.model.StartBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TelegramApiException, IOException {
        StartBot.startTgBot();
    }
}

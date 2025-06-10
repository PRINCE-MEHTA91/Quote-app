package com.example.quoteapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView quoteText;
    Button nextButton;

    OkHttpClient client = new OkHttpClient();

    // Local fallback quotes (50 total)
    String[] backupQuotes = {
            "Believe in yourself!",
            "One step at a time.",
            "Progress over perfection.",
            "Make today count!",
            "Keep moving forward!",
            "You’ve got this!",
            "Don’t watch the clock; do what it does. Keep going.",
            "Push yourself, because no one else is going to do it for you.",
            "Success doesn’t come to you, you go to it.",
            "Failure is the condiment that gives success its flavor.",
            "Hardships often prepare ordinary people for an extraordinary destiny.",
            "Act as if what you do makes a difference. It does.",
            "Your limitation—it's only your imagination.",
            "Dream it. Wish it. Do it.",
            "Sometimes later becomes never. Do it now.",
            "Great things never come from comfort zones.",
            "Success doesn’t just find you. You have to go out and get it.",
            "The harder you work for something, the greater you’ll feel when you achieve it.",
            "Dream bigger. Do bigger.",
            "Don’t stop when you’re tired. Stop when you’re done.",
            "Little things make big days.",
            "It’s going to be hard, but hard does not mean impossible.",
            "Don’t wait for opportunity. Create it.",
            "Be a warrior, not a worrier.",
            "Doubt kills more dreams than failure ever will.",
            "The only bad workout is the one that didn’t happen.",
            "Discipline is choosing between what you want now and what you want most.",
            "Success is the sum of small efforts, repeated daily.",
            "Your mindset determines your success.",
            "You are capable of amazing things.",
            "Today is another chance to get better.",
            "Start strong, finish stronger.",
            "You don’t have to be perfect to be amazing.",
            "Stop doubting yourself. Work hard and make it happen.",
            "Winners are not people who never fail, but people who never quit.",
            "Difficult does not mean impossible.",
            "You define your own life. Don’t let others do it for you.",
            "Don’t limit your challenges. Challenge your limits.",
            "Believe you can and you're halfway there.",
            "You were born to do great things.",
            "Stay focused and never give up.",
            "One day at a time, one step at a time.",
            "Grind in silence, let your success make the noise.",
            "Success starts with self-discipline.",
            "Turn your dreams into plans.",
            "Do something today that your future self will thank you for.",
            "You're one decision away from a totally different life.",
            "You are your only limit.",
            "The best view comes after the hardest climb.",
            "Success is built on consistency."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = findViewById(R.id.quoteText);
        nextButton = findViewById(R.id.nextButton);

        // Fetch quote on start
        fetchQuote();

        // Fetch new quote on button click
        nextButton.setOnClickListener(v -> fetchQuote());
    }

    private void fetchQuote() {
        Request request = new Request.Builder()
                .url("https://api.quotable.io/random")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    int i = new Random().nextInt(backupQuotes.length);
                    String day = getDayOfWeek();
                    quoteText.setText("Happy " + day + "! 🌞\n\n" + backupQuotes[i]);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    try {
                        JSONObject json = new JSONObject(jsonData);
                        final String quote = json.getString("content");
                        final String author = json.getString("author");
                        final String day = getDayOfWeek();

                        runOnUiThread(() -> quoteText.setText("Happy " + day + "! 🌞\n\n\"" + quote + "\"\n\n- " + author));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private String getDayOfWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        return sdf.format(new Date());
    }
}

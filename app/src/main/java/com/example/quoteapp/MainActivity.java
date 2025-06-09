package com.example.quoteapp;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView quoteText;
    Button nextButton;

    // Quotes mapped to days of the week
    String[] dayQuotes = {
            "Sunday: Recharge and reflect 🌞",
            "Monday: New week, new goals 💪",
            "Tuesday: Keep pushing forward 🚀",
            "Wednesday: Halfway to success 🧗‍♂️",
            "Thursday: Stay focused and driven 🎯",
            "Friday: Finish strong! 🏁",
            "Saturday: Rest, but never quit 🛌"
    };

    String[] extraQuotes = {
            "Believe in yourself!",
            "Progress, not perfection.",
            "One step at a time.",
            "Make today count!",
            "You’ve got this!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = findViewById(R.id.quoteText);
        nextButton = findViewById(R.id.nextButton);

        // Get today's quote
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 1=Sunday, 7=Saturday
        quoteText.setText(dayQuotes[dayOfWeek - 1]);

        // Button for additional random motivation
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = new Random().nextInt(extraQuotes.length);
                quoteText.setText(extraQuotes[i]);
            }
        });
    }
}


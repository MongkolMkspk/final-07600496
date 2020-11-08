package th.ac.su.cp.speedrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import th.ac.su.cp.speedrecords.db.AppDatabase;
import th.ac.su.cp.speedrecords.model.Calculator;
import th.ac.su.cp.speedrecords.model.Speed;
import th.ac.su.cp.speedrecords.util.AppExecutors;

public class AddSpeedActivity extends AppCompatActivity {
    private EditText durationEditText,distanceEditText;
    private Calculator c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_speed);

        durationEditText = findViewById(R.id.duration_editTextNumberDecimal);
        distanceEditText = findViewById(R.id.distance_editTextNumberDecimal);
        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sString = distanceEditText.getText().toString().trim();//.trim() delete space when user put space
                String tString = durationEditText.getText().toString().trim();
                String speedString = "0";
                boolean overLimit = false;
                if (tString.equals("0")) {
                    Toast toast = Toast.makeText(AddSpeedActivity.this, "Time must be greater than zero.", Toast.LENGTH_LONG);
                    toast.show();

                } else if (sString.isEmpty() || tString.isEmpty()) {
                    Toast toast = Toast.makeText(AddSpeedActivity.this, "Distance and time are required.", Toast.LENGTH_LONG);
                    toast.show();
                } else {

                    double s = Double.parseDouble(sString);
                    double t = Double.parseDouble(tString);
                    c = new Calculator(s, t);
                    double answer = c.calculate();
                    speedString = String.format(
                            Locale.getDefault(), "%.2f", answer
                    );
                    if (answer > 80) {
                        overLimit = true;
                    }
                }

                final Speed speed = new Speed(0,speedString,sString,tString,overLimit);

                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getInstance(AddSpeedActivity.this);
                        db.userDao().addSpeed(speed);
                        finish();
                    }
                });

            }
        });


    }
}
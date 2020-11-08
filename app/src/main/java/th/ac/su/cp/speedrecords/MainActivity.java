package th.ac.su.cp.speedrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import th.ac.su.cp.speedrecords.adapter.UserAdapter;
import th.ac.su.cp.speedrecords.db.AppDatabase;
import th.ac.su.cp.speedrecords.model.Speed;
import th.ac.su.cp.speedrecords.util.AppExecutors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private RecyclerView mRecyclerView;
    private int over;

    protected void onResume() {
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final Speed[] speeds = db.userDao().getAllSpeed();
                for (int i=0;i<speeds.length;i++){
                    if (speeds[i].overLimit){
                        over+=1;
                    }
                }

                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        UserAdapter adapter = new UserAdapter(MainActivity.this, speeds);
                        mRecyclerView.setAdapter(adapter);
                        String o = String.valueOf(over);
                    }
                });

        /*String msg = "";
        for (User u : users) {
          Log.i(TAG, u.firstName + " " + u.lastName);
          msg += String.format(
              Locale.getDefault(),
              "%s %s %s\n",
              u.firstName, u.lastName, DateFormatter.formatForUi(u.birthDate)
          );
        }

        final String message = msg;
        executors.mainThread().execute(new Runnable() {
          @Override
          public void run() { // main thread
            new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
          }
        });*/
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.speed_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddSpeedActivity.class);
                startActivity(i);
            }
        });
    }
}
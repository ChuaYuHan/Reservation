package sg.edu.rp.c346.id19020844.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    EditText name, mobileNum, sog;
    CheckBox cbsEnable;
    DatePicker dp;
    TimePicker tp;
    Button btnReserve, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editName);
        mobileNum = findViewById(R.id.editMobileNum);
        sog = findViewById(R.id.editSOG);
        cbsEnable = findViewById(R.id.checkboxSmoke);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        btnReserve = findViewById(R.id.reserve);
        btnClear = findViewById(R.id.clear);
        dp.setMinDate(System.currentTimeMillis());

        resetAll();

        btnReserve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(name.getText().toString().trim().length() != 0 && mobileNum.getText().toString().trim().length() != 0 &&
                        sog.getText().toString().trim().length() != 0){
                    String Pname = name.getText().toString().trim();
                    String Pnum = mobileNum.getText().toString().trim();
                    String Psog = sog.getText().toString().trim();
                    int year = dp.getYear();
                    int month = dp.getMonth();
                    int day = dp.getDayOfMonth();
                    int hour = tp.getCurrentHour();
                    int min = tp.getCurrentMinute();

                    String smoke = "";
                    if(cbsEnable.isChecked()){
                        smoke="Yes";
                    }
                    else{
                        smoke="No";
                    }

                    String reserve = String.format("Name:  %s\nMobile Number: %s\nSize of group: %s\nDate: %d/%d/%d\nTime: %d:%d" +
                            "\nSmoking table: %s", Pname, Pnum, Psog, day, month, year, hour, min, smoke);

                    Toast confirm = Toast.makeText(MainActivity.this, reserve, Toast.LENGTH_LONG);
                    confirm.show();
                }
                else if(name.getText().toString().trim().length() == 0 && mobileNum.getText().toString().trim().length() != 0 &&
                        sog.getText().toString().trim().length() != 0){
                    Toast.makeText(MainActivity.this, "Please enter your full name", Toast.LENGTH_LONG).show();
                }
                else if(name.getText().toString().trim().length() != 0 && mobileNum.getText().toString().trim().length() == 0 &&
                        sog.getText().toString().trim().length() != 0){
                    Toast.makeText(MainActivity.this, "Please enter your mobile number", Toast.LENGTH_LONG).show();
                }
                else if(name.getText().toString().trim().length() != 0 && mobileNum.getText().toString().trim().length() != 0 &&
                        sog.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter size of group", Toast.LENGTH_LONG).show();
                }
            }

        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(!(hourOfDay >= 8 && hourOfDay <= 20)){
                    Toast.makeText(MainActivity.this, "Valid timing from 8am to 8:59pm", Toast.LENGTH_SHORT).show();
                    updateTime(8, 0);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                resetAll();
            }

        });

    }

    private void updateTime(int hour, int min) {
        tp.setCurrentHour(hour);
        tp.setCurrentMinute(min);
    }

    private void resetAll() {
        updateTime(19, 30);
        dp.updateDate(2020, 5, 1);
        name.setText("");
        mobileNum.setText("");
        sog.setText("");
        cbsEnable.setChecked(false);
    }

}

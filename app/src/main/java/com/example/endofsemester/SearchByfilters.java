package com.example.endofsemester;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

public class SearchByfilters extends AppCompatActivity {


    public static ArrayList<Ulam> ulamArrayList = new ArrayList<>();
    public static ArrayList<Ulam> resaultUlaml = new ArrayList<>();
    private SeekBar seekBarRange;
    private TextView sumRange;
    private SeekBar seekBarInvited;
    private TextView sumInvited;
    private Spinner spinnerCitys;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_byfilters);
        addNewUlam();
        seekBarInvited = findViewById(R.id.seekBar_invited);
        seekBarRange = findViewById(R.id.seekBar_range);

        sumRange = findViewById(R.id.sum_range);
        sumInvited = findViewById(R.id.sum_invited);
        Button button = findViewById(R.id.check_the_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTheData();
            }
        });
        metodSeekBarRange();
        metodSeekBarInvited();
        addSpinner();
        onResume();

    }

    private void addNewUlam() {
        ulamArrayList=new ArrayList<>();
        ulamArrayList.add(new Ulam(20, 200, "תל אביב", R.drawable.aahuza,"http://achuza.co.il"));
        ulamArrayList.add(new Ulam(500, 350, "תל אביב", R.drawable.aatsula,"http://www.haatzula.co.il"));
        ulamArrayList.add(new Ulam(300, 300, "תל אביב", R.drawable.dereh_ayaar,"http://www.dhj.co.il"));
        ulamArrayList.add(new Ulam(500, 290, "תל אביב", R.drawable.emilia,"http://www.emiliaevents.co.il"));
        ulamArrayList.add(new Ulam(270, 600, "תל אביב", R.drawable.gany_aysvi,"https://ganeyhazvi.co.il"));
        ulamArrayList.add(new Ulam(500, 30, "תל אביב", R.drawable.adia,"https://www.adia.co.il"));
        ulamArrayList.add(new Ulam(500, 30, "תל אביב", R.drawable.oforia," http://www.euphoria-events.co.il"));
        ulamArrayList.add(new Ulam(500, 30, "תל אביב", R.drawable.venetsia,"http://www.venicevents.co.il"));
//        ulamArrayList.add(new Ulam(500, 500, "vfgh",R.drawable.n1,3));
//        ulamArrayList.add(new Ulam(500, 500, "vfgh",R.drawable.n1,4));
//        ulamArrayList.add(new Ulam(500, 500, "vfgh",R.drawable.n1,4));
//        ulamArrayList.add(new Ulam(12, 500, "vfgh",R.drawable.n1,4));
//        ulamArrayList.add(new Ulam(500, 500, "vfgh",R.drawable.n1,4));
//        ulamArrayList.add(new Ulam(12, 500, "vfgh",R.drawable.n1,0));
//        ulamArrayList.add(new Ulam(12, 34, "vfgh",R.drawable.n1,0));
//        ulamArrayList.add(new Ulam(12, 34, "vfgh",R.drawable.n1,0));
//        ulamArrayList.add(new Ulam(12, 34, "vfgh",R.drawable.n1,0));
//        ulamArrayList.add(new Ulam(12, 34, "vfgh",R.drawable.n1,0));


    }

    /*  object.getPrice() <= seekBarRange.getProgress()
                            && object.getMuzmanim() <= seekBarInvited.getProgress()
                            && spinnerCitys.getSelectedItem().toString().contains(object.getCity())*/
    private void checkTheData() {

        for (Ulam object : ulamArrayList) {

            if (
                    object.getPrice() <= seekBarRange.getProgress()
                            && object.getMuzmanim() <= seekBarInvited.getProgress()
                            && spinnerCitys.getSelectedItem().toString().contains(object.getCity())
            ) {
                resaultUlaml.add(object);

            }

       /*  else if (resaultUlaml.isEmpty()){

                Toast.makeText(this, "לא נמצאו נתונים ", Toast.LENGTH_LONG).show();

            }
*/



    }
 if (resaultUlaml.isEmpty()){


            Toast.makeText(this, "לא נמצאו אולמות מתאימים  :/ ", Toast.LENGTH_LONG).show();
     startActivity(new Intent(this, R_V.class));

        }
 else

        startActivity(new Intent(this, MainActivity_checking.class));
    }


    @Override
    public void onResume() {
        super.onResume();

        resaultUlaml.clear();
    }


    private void metodSeekBarRange() {

        seekBarRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 10;
                progress = progress * 10;
                sumRange.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void metodSeekBarInvited() {
        seekBarInvited.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                progress = progress / 10;
                progress = progress * 10;
                sumInvited.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void addSpinner() {
        Vector<String> str = new Vector<String>();
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(getAssets().open("city.txt")));
            String line = in.readLine();
            int index = 0;
            while (line != null) {
                str.add(line);
                line = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        spinnerCitys = findViewById(R.id.spinner1);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, str);

        spinnerCitys.setAdapter(adapter);
        spinnerCitys.setSelection(2);

    }
}


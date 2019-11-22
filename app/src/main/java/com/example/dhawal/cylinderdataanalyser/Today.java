package com.example.dhawal.cylinderdataanalyser;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.circularprogressbar.CircularProgressBar;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.Locale;

public class Today extends Fragment implements DatePickerListener {

    TextView day , datemon,percent;
    DatabaseReference rootRef;
    CircularProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =inflater.inflate(R.layout.fragment_today, container, false);
        Toast.makeText(getContext(),"Select Date",Toast.LENGTH_LONG).show();

        rootRef = FirebaseDatabase.getInstance().getReference();
        percent = v.findViewById(R.id.percent);

        HorizontalPicker picker = (HorizontalPicker) v.findViewById(R.id.datePicker);
        day = v.findViewById(R.id.day);
        datemon = v.findViewById(R.id.Dtyear);

        Date d = new Date();
        datemon.setText(DateFormat.format("d, MMMM", d.getTime()));
        day.setText(DateFormat.format("EEEE",d.getTime()));
        progressBar = v.findViewById(R.id.progress_bar);
        progressBar.setProgress(75);
        progressBar.setBackgroundStrokeColor(Color.GREEN);
       // progressBar.setBackgroundColor(Color.RED);
        progressBar.setForegroundStrokeColor(Color.GREEN);
        // initialize it and attach a listener
        picker
                .setListener(this)
                .init();
        return v;
    }

    @Override
    public void onDateSelected(DateTime dateSelected) {

        day.setText(dateSelected.toString("EEEE", Locale.getDefault()));
        String s = dateSelected.toString("dd", Locale.getDefault())+ ", " + dateSelected.toString("MMMM", Locale.getDefault());
        datemon.setText(s);

        String str = dateSelected.toString("dd-MM-yyyy",Locale.getDefault());
       // Toast.makeText(getContext(),str,Toast.LENGTH_LONG).show();

        rootRef.child("value").child(str)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue() != null) {
                            String s = dataSnapshot.getValue().toString();

                           // Toast.makeText(getContext(),""+(Float.parseFloat(s) -  16 ),Toast.LENGTH_LONG).show();
                            int p = (int) (((Float.parseFloat(s) -  16 )*100/ 14) );

                            percent.setText("" + p+"%");
                            progressBar.setProgress(p);
                            if(p > 60)
                            {
                                progressBar.setForegroundStrokeColor(Color.GREEN);

                            }
                            if(p <= 60 && p > 25)
                            {
                                progressBar.setForegroundStrokeColor(Color.parseColor("#FFA500"));
                            }
                            else if(p <= 25)
                            {
                                progressBar.setForegroundStrokeColor(Color.RED);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        }
}

package com.example.dhawal.cylinderdataanalyser;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

import org.joda.time.DateTime;

import java.util.Date;
import java.util.Locale;

public class Today extends Fragment implements DatePickerListener {

    TextView day , datemon;

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

        HorizontalPicker picker = (HorizontalPicker) v.findViewById(R.id.datePicker);
        day = v.findViewById(R.id.day);
        datemon = v.findViewById(R.id.Dtyear);

        Date d = new Date();
        datemon.setText(DateFormat.format("d, MMMM", d.getTime()));
        day.setText(DateFormat.format("EEEE",d.getTime()));
        CircularProgressBar progressBar = v.findViewById(R.id.progress_bar);
        progressBar.setProgress(75);
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
    }
}

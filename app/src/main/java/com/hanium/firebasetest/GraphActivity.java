package com.hanium.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class GraphActivity extends AppCompatActivity {

    private LineChart chart_1;
    private LineChart chart_2;

    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;
    private DatabaseReference databaseReference;
    private ArrayList<User> arrayList;


    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        LineChart chart_1 = (LineChart) findViewById(R.id.chart_1);

        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance(); // 파이어베이스데이터베이스 연동

        valueEventListener = database.getReference("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> entries = new ArrayList<Entry>();
                ArrayList<Entry> entries_2 = new ArrayList<Entry>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    entries.add(new Entry(user.getCurrent(), user.getVolt()));
                    /*entries_2.add(new Entry(0, user.getCurrent()));*/

                    showChart(entries);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.e("arraydata", String.valueOf(error.toException()));
            }
        });


    }

    private void showChart(ArrayList<Entry> entries) {
        lineDataSet = new LineDataSet(entries, "전압");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(4);
        lineDataSet.setCircleColor(Color.parseColor("#000000"));
        lineDataSet.setCircleHoleColor(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(true);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueTextSize(10);
        chart_1.setData(lineData);
        chart_1.invalidate();

        Legend l = chart_1.getLegend();
        l.setEnabled(true);
        l.setFormSize(10f);
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);

        YAxis leftAxis_1 = chart_1.getAxisLeft();
        leftAxis_1.setEnabled(true);
        leftAxis_1.setDrawGridLines(true);
        leftAxis_1.setTextSize(15);

        YAxis rightAxis_1 = chart_1.getAxisRight();
        rightAxis_1.setEnabled(false);
        chart_1.getXAxis().setDrawAxisLine(true);
        chart_1.getXAxis().setEnabled(true);
        chart_1.getXAxis().setDrawGridLines(true);
        chart_1.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart_1.getXAxis().setTextSize(15);
    }

}


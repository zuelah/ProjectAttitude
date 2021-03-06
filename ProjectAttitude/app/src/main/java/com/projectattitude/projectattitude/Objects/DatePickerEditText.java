/*
 * MIT License
 *
 * Copyright (c) 2017 CMPUT301W17T12
 * Authors rsauveho vuk bfleyshe henrywei cs3
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.projectattitude.projectattitude.Objects;

/**
 * Created by rfsh on 2017-03-07.
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * This object represents the DatePickerEditText when creating and editing moods.
 * This allows the user to select the appropriate time for their mood.
 * @See CreateMoodActivity
 * @See EditMoodActivity
 */
 //Source: http://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext


public class DatePickerEditText implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText editText;
    private int day;
    private int month;
    private int year;
    private Context _context;

    /**
     * Sets the date text.
     * @param context Date
     * @param editTextViewID ID of the editView
     */
    public DatePickerEditText(Context context, int editTextViewID)
    {
        Activity act = (Activity)context;
        this.editText = (EditText)act.findViewById(editTextViewID);
        this.editText.setOnClickListener(this);
        this._context = context;
    }

    /**
     * Updates the views date.
     * @param view The view.
     * @param year The year.
     * @param monthOfYear The month.
     * @param dayOfMonth The day.
     */
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
        updateDisplay();
    }

    /**
     * Sets the date.
     * @param date The date.
     */
    public void setDate(Date date) {
        this.year = date.getYear();
        this.month = date.getMonth();
        this.day = date.getDay();
        updateDisplay();
    }

    /**
     * Sets the date.
     * @param year The year.
     * @param month The month.
     * @param day The day.
     */
    public void setDate(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
        updateDisplay();
    }

    /**
     * On clicking the date, bring up the date picker.
     * @param v The view object.
     */
    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(_context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        dialog.show();
    }

    /**
     * Getting the date.
     * @return Returns the date
     */
    public Date getDate(){
        String date_str = editText.getText().toString();
        Log.d("DATE:_getdate - str", date_str);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        try {
            Date date = sdf.parse(date_str);
            Log.d("DATE:_getdate - date", date.toString());
            Date currTime = new Date(System.currentTimeMillis());
            return new Date(date.getYear(), date.getMonth(), date.getDate(),
                    currTime.getHours(), currTime.getMinutes(), currTime.getSeconds());
        } catch (ParseException e) {
            return new Date(System.currentTimeMillis());
        }
    }

    /**
     * updates the date in the date EditText
     */
    private void updateDisplay() {
        editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(year).append("-").append(month + 1).append("-").append(day));
    }
}

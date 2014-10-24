package com.chandu.dolist;

import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
	
	// Use the current date as the default date in the picker
	final Calendar c = Calendar.getInstance();
    int _year = c.get(Calendar.YEAR);
    int _month = c.get(Calendar.MONTH);
    int _day = c.get(Calendar.DAY_OF_MONTH);
    static String _date;
    
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, _year, _month, _day);
    }
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
		_date = (month+1)+"-"+day+"-"+year;
	}
}

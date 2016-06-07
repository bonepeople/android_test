package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * 选择地址
 * 
 * @author bonepeople
 */
public class Activity_location extends DEBUG_Activity
{

	private EditText _province, _county, _city;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);

		_province = (EditText) findViewById(R.id.edittext_province);
		_county = (EditText) findViewById(R.id.edittext_county);
		_city = (EditText) findViewById(R.id.edittext_city);
	}

	public void commit(View v)
	{
		Intent _intent = new Intent();
		int proid = 1001;
		int cityid = 100101;
		int countyId = 10010104;
		try
		{
			proid = Integer.parseInt(_province.getText().toString());
			cityid = Integer.parseInt(_city.getText().toString());
			countyId = Integer.parseInt(_county.getText().toString());
		}
		catch (Exception e)
		{
			System.out.println("not number");
		}
		_intent.putExtra("provinceId", proid);
		_intent.putExtra("cityId", cityid);
		_intent.putExtra("countyId", countyId);
		setResult(RESULT_OK, _intent);
		finish();
	}
}

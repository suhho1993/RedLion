package com.dkmobile.lionchef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import logic.Dish;

public class RecipeEdit extends Activity {
	private Toast mToast;
	Dish currentDish;
	EditText name;
	EditText url;

	Button save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_dish);

		name = (EditText) findViewById(R.id.edit_name);
		url = (EditText) findViewById(R.id.edit_url);
		save = (Button) findViewById(R.id.edit_save);

		currentDish = (Dish) getIntent().getSerializableExtra("Dish");

		name.setText(currentDish.getName());
		url.setText(currentDish.getUrl());

	}

	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.edit_save: {
			String mName;
			String mUrl;
			mName = name.getText().toString();
			mUrl = url.getText().toString();
			if (mName.equals("") && mUrl.equals("")) {
				showToast("Enter Name & Url");
				break;
			} else if (mName.equals("")) {
				showToast("Enter Name");
				break;
			} else if (mUrl.equals("")) {
				showToast("Enter Name");
				break;
			}
			Dish dish = new Dish(mName, mUrl);
			Intent curDish = new Intent();
			curDish.putExtra("Dish", dish);
			curDish.putExtra("delete", "false");
			setResult(RESULT_OK, curDish);
			finish();
			break;
		}
		case R.id.edit_delete: {
			String mName;
			String mUrl;
			mName = name.getText().toString();
			mUrl = url.getText().toString();
			Dish dish = new Dish(mName, mUrl);
			Intent curDish = new Intent();
			curDish.putExtra("Dish", dish);
			curDish.putExtra("delete", "true");
			setResult(RESULT_OK, curDish);
			finish();
			break;

		}
		}
	}

	private void showToast(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

}

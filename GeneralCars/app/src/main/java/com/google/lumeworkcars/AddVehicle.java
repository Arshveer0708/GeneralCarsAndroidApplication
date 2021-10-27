package com.google.lumeworkcars;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddVehicle extends AppCompatActivity {
	MaterialEditText makeOfVehicle;
	MaterialEditText vehicleModel;
	MaterialEditText conditionOfVehicle;
	MaterialEditText yearOfVehicle;
	MaterialEditText numberOfDoors;
	MaterialEditText priceOfVehicle;
	MaterialEditText colorOfVehicle;
	MaterialEditText cylindersInVehicle;
	ImageView thumbnail;
	Button saveBtn, browse;
	private static final int result = 100;
	DatabaseHelper mDatabaseHelper;
	private Uri mUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_vehicles);
		makeOfVehicle = findViewById(R.id.makersOfCars);
		vehicleModel = findViewById(R.id.modelOfvehicle);
		conditionOfVehicle = findViewById(R.id.vehicleCondition);
		cylindersInVehicle = findViewById(R.id.vehicleCylinder);
		yearOfVehicle = findViewById(R.id.modelYear);
		numberOfDoors = findViewById(R.id.doorsInCar);
		priceOfVehicle = findViewById(R.id.vehiclePrice);
		colorOfVehicle = findViewById(R.id.colorOfVehicle);
		saveBtn = findViewById(R.id.saveDetails);
		browse = findViewById(R.id.browse);

		ActionBar mActionBar = getSupportActionBar();
		mActionBar.setTitle("Add Vehicle");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		thumbnail = findViewById(R.id.imageview_edit);

		mDatabaseHelper = new DatabaseHelper(this);
		browse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getImage();
			}
		});


		saveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (makeOfVehicle.getText().toString().isEmpty() || vehicleModel.getText().toString().isEmpty() || conditionOfVehicle.getText().toString().isEmpty()
						|| yearOfVehicle.getText().toString().isEmpty() || colorOfVehicle.getText().toString().isEmpty() || cylindersInVehicle.getText().toString().isEmpty()) {
					Toast.makeText(AddVehicle.this, "Please check all your details once!", Toast.LENGTH_SHORT).show();
				} else if (numberOfDoors.getText().length() == 0) {
					Toast.makeText(AddVehicle.this, "Seems doors are not in car!!", Toast.LENGTH_SHORT).show();
				} else {
					InputStream iStream = null;
					byte[] inputData = null;
					try {
						iStream = getContentResolver().openInputStream(mUri);
						inputData = Utils.getBytes(iStream);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}


					mDatabaseHelper.addVehicles(makeOfVehicle.getText().toString(), vehicleModel.getText().toString(), conditionOfVehicle.getText().toString(),
							cylindersInVehicle.getText().toString(), yearOfVehicle.getText().toString(), numberOfDoors.getText().toString(),
							priceOfVehicle.getText().toString(), colorOfVehicle.getText().toString(), inputData, "");
					startActivity(new Intent(AddVehicle.this, ViewAllVehicle.class));
					finish();
				}
			}
		});

	}

	void getImage() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"), result);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == result) {
				mUri = data.getData();
				if (null != mUri) {
					thumbnail.setImageURI(mUri);
				}
			}
		}
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}
}
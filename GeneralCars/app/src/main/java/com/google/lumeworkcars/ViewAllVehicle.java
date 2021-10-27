package com.google.lumeworkcars;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAllVehicle extends AppCompatActivity {
	ArrayList<Vehicles> mArrayList;
	RecyclerView mRecyclerView;
	DatabaseHelper databaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_all_vehicles);
		databaseHelper = new DatabaseHelper(this);

		mRecyclerView = findViewById(R.id.recyclerview);
		viewAllVehicles();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.add_vehicle) {

			Intent mIntent = new Intent(this, AddVehicle.class);
			startActivity(mIntent);


		}
		if (id == R.id.company_details) {
			Intent mIntent=new Intent(ViewAllVehicle.this,CompanyDetails.class);
			startActivity(mIntent);
		}
		return super.onOptionsItemSelected(item);
	}

	public void viewAllVehicles() {
		mArrayList = new ArrayList<>(databaseHelper.fetchVehicles());
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		final RecycleViewAdapter mAdapter = new RecycleViewAdapter(getApplicationContext(), mArrayList);
		mRecyclerView.setAdapter(mAdapter);
		mAdapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(int item) {
				popUpWindow(item);
			}
		});
	}

	public void popUpWindow(final int position) {
		final LayoutInflater layoutInflater = LayoutInflater.from(ViewAllVehicle.this);
		final View mView = layoutInflater.inflate(R.layout.update_vehicles, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setView(mView);

		final EditText makers = mView.findViewById(R.id.makersOfCars);
		final EditText model_of_vehicle = mView.findViewById(R.id.modelOfvehicle);
		final EditText condition_of_vehicle = mView.findViewById(R.id.vehicleCondition);
		final EditText engine_cylinders_of_vehicle = mView.findViewById(R.id.vehicleCylinder);
		final EditText year_of_vehicle = mView.findViewById(R.id.modelYear);
		final EditText number_of_doors_of_vehicle = mView.findViewById(R.id.doorsInCar);
		final EditText price_of_vehicle = mView.findViewById(R.id.vehiclePrice);
		price_of_vehicle.setEnabled(true);
		final EditText color_of_vehicle = mView.findViewById(R.id.colorOfVehicle);
		final EditText date_sold_of_vehicle = mView.findViewById(R.id.datesold_of_vehicle);
		date_sold_of_vehicle.setEnabled(true);
		final ImageView thumbnail = mView.findViewById(R.id.imageview_edit);

		Button save = mView.findViewById(R.id.saveDetails);
		makers.setText(mArrayList.get(position).getMakers());
		model_of_vehicle.setText(mArrayList.get(position).getModel_of_vehicle());
		condition_of_vehicle.setText(mArrayList.get(position).getColor_of_vehicle());
		engine_cylinders_of_vehicle.setText(mArrayList.get(position).getEngine_cylinders_of_vehicle());
		year_of_vehicle.setText(mArrayList.get(position).getYear_of_manufacturing());
		number_of_doors_of_vehicle.setText(mArrayList.get(position).getNumber_of_doors());
		price_of_vehicle.setText(mArrayList.get(position).getPrice_of_vehicle());
		color_of_vehicle.setText(mArrayList.get(position).getColor_of_vehicle());
		thumbnail.setImageBitmap(Utils.getImage(mArrayList.get(position).getImage()));
		final AlertDialog alertDialog = alertDialogBuilder.create();

		alertDialog.show();
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (price_of_vehicle.getText().toString().isEmpty() || date_sold_of_vehicle.getText().toString().isEmpty()) {
					Toast.makeText(ViewAllVehicle.this, "Field is empty!!", Toast.LENGTH_SHORT).show();
				} else {
					databaseHelper.updateDetails(mArrayList.get(position).getId(), makers.getText().toString(), model_of_vehicle.getText().toString(), condition_of_vehicle.getText().toString(),
							engine_cylinders_of_vehicle.getText().toString(), year_of_vehicle.getText().toString(), number_of_doors_of_vehicle.getText().toString(),
							price_of_vehicle.getText().toString(), color_of_vehicle.getText().toString(), date_sold_of_vehicle.getText().toString());
					viewAllVehicles();
					alertDialog.cancel();
				}
			}
		});

	}


}
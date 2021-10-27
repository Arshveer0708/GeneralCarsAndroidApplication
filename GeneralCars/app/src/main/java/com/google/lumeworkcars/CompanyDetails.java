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
import java.util.ArrayList;

public class CompanyDetails extends AppCompatActivity {
	private static final int result = 100;
	DatabaseAdapter mDatabaseAdapter;
	private Uri mUri;
	Button saveCompany;
	ImageView companyLogo;
	MaterialEditText name;
	MaterialEditText address;
	ArrayList<CompanyModel> mArrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_company_details);
		mDatabaseAdapter = new DatabaseAdapter(this);
		saveCompany = findViewById(R.id.saveCompanyData);
		companyLogo = findViewById(R.id.companyLogo);
		name = findViewById(R.id.nameCompany);
		address = findViewById(R.id.address);
		mArrayList = new ArrayList<>(mDatabaseAdapter.fetchDetails());

		ActionBar mActionBar = getSupportActionBar();
		mActionBar.setTitle("Add Vehicle");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);


		CompanyModel mCompanyModel = new CompanyModel();
		name.setText(mCompanyModel.getName());
		companyLogo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getImage();
			}
		});
		name.setText(mArrayList.get(0).getName());
		address.setText(mArrayList.get(0).getAddress());
		companyLogo.setImageBitmap(Utils.getImage(mArrayList.get(0).getImage()));

		saveCompany.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (name.getText().toString().isEmpty() || address.getText().toString().isEmpty() || companyLogo.getDrawable() == null) {
					Toast.makeText(CompanyDetails.this, "Please check all your details once!", Toast.LENGTH_SHORT).show();
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

					if (!name.getText().toString().isEmpty()){
						mDatabaseAdapter.updateDetails(mArrayList.get(0).getId(),name.getText().toString(), address.getText().toString(), inputData);
						startActivity(new Intent(CompanyDetails.this, ViewAllVehicle.class));
						finish();
					}
					else{
						mDatabaseAdapter.addCompanyDetails(name.getText().toString(), address.getText().toString(), inputData);
						startActivity(new Intent(CompanyDetails.this, ViewAllVehicle.class));
						finish();
					}

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
					companyLogo.setImageURI(mUri);
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
package com.google.lumeworkcars;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
	Context context;
	ArrayList<Vehicles> mList;
	DatabaseHelper mDatabaseHelper;
	private OnItemClickListener listener;

	public interface OnItemClickListener {
		void onItemClick(int item);
	}

	public RecycleViewAdapter(Context context, ArrayList<Vehicles> list) {
		this.context = context;
		this.mList = list;
	}

	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.custom_layout, parent, false);
		return new MyViewHolder(view, listener);
	}

	public void setOnItemClickListener(OnItemClickListener itemClickListener) {
		listener = itemClickListener;
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
		holder.makers.setText("Make- " + mList.get(position).getMakers());
		holder.model_of_vehicle.setText("Model of Vehicle- " + mList.get(position).getModel_of_vehicle());
		holder.condition_of_vehicle.setText("Conditon of Vehicle- " + mList.get(position).getColor_of_vehicle());
		holder.engine_cylinders_of_vehicle.setText("Engine Cylinders- " + mList.get(position).getEngine_cylinders_of_vehicle());
		holder.yearManufacturing.setText("Year- " + mList.get(position).getYear_of_manufacturing());
		holder.number_of_doors.setText("Number of doors- " + mList.get(position).getNumber_of_doors());
		holder.price_of_vehicle.setText("CAD " + mList.get(position).getPrice_of_vehicle());
		holder.color_of_vehicle.setText("Color of Vehicle- " + mList.get(position).getColor_of_vehicle());
		holder.image.setImageBitmap(Utils.getImage(mList.get(position).getImage()));
		if (mList.get(position).getDate_sold().length() != 0) {
			holder.date_sold.setVisibility(View.VISIBLE);
			holder.date_sold.setText("Date Sold- " + mList.get(position).getDate_sold());
		}
		mDatabaseHelper = new DatabaseHelper(context);


	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {
		TextView makers, model_of_vehicle, condition_of_vehicle, engine_cylinders_of_vehicle, yearManufacturing, number_of_doors, price_of_vehicle, color_of_vehicle, date_sold, _id;
		ImageView image;

		public MyViewHolder(@NonNull View itemView, final OnItemClickListener mListener) {
			super(itemView);
			makers = itemView.findViewById(R.id.makers);
			model_of_vehicle = itemView.findViewById(R.id.model_of_vehicle);
			condition_of_vehicle = itemView.findViewById(R.id.condition_of_vehicle);
			engine_cylinders_of_vehicle = itemView.findViewById(R.id.engine_cylinder_of_vehicle);
			yearManufacturing = itemView.findViewById(R.id.year_of_vehicle);
			number_of_doors = itemView.findViewById(R.id.doors_of_vehicle);
			price_of_vehicle = itemView.findViewById(R.id.price_of_vehicle);
			color_of_vehicle = itemView.findViewById(R.id.color_of_vehicle);
			date_sold = itemView.findViewById(R.id.datesold_of_vehicle);
			image = itemView.findViewById(R.id.imageset);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					if (mListener != null) {
						int position = getAdapterPosition();
						if (position != RecyclerView.NO_POSITION) {
							listener.onItemClick(position);
						}
					}
				}
			});
		}
	}


}


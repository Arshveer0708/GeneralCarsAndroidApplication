package com.google.lumeworkcars;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

	public static byte[] getImageBytes(Bitmap bitmap) {
		ByteArrayOutputStream iByteStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, iByteStream);
		return iByteStream.toByteArray();
	}

	public static Bitmap getImage(byte[] image) {
		return BitmapFactory.decodeByteArray(image, 0, image.length);
	}

	public static byte[] getBytes(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
		int size = 1024;
		byte[] buffer = new byte[size];

		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			byteArrayStream.write(buffer, 0, len);
		}
		return byteArrayStream.toByteArray();
	}
}

package com.ledomatic.android;

import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ledomatic.android.ColorPickerView.OnColorChangedListener;

public class Main extends Activity implements OnColorChangedListener {
	private TextView statusText;
	private LinearLayout toggleContainer;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		toggleContainer = (LinearLayout) findViewById(R.id.toggleContainer);
		statusText = (TextView) findViewById(R.id.textStatus);
	}
	
	public void colorChanged( int color) {
		
		final int c = color;
		new Thread(new Runnable() {
			public void run() {
			 	GAEAdapter.getInstance().setRGBValue("L1", Integer.toHexString( Color.red( c))  + Integer.toHexString( Color.green( c))  + Integer.toHexString( Color.blue(c)));
				runOnUiThread(new Runnable() {
					public void run() {
						//status.setText("");

					}
				});
			}
		}).start();
		
		Log.i( "Main", "Red: " + Integer.toHexString( Color.red( color))  + " - Green: " + Integer.toHexString( Color.green( color)) + " - Blue: " + Integer.toHexString( Color.blue( color)));
	}

	@Override
	protected void onResume() {

		new Thread(new Runnable() {

			public void run() {
				final List<String> devices = GAEAdapter.getInstance()
						.getDevices();
				Main.this.checkDevices(devices);

			}
		}).start();

		super.onResume();
	}

	protected void checkDevices(final List<String> devices) {
		runOnUiThread(new Runnable() {
			public void run() {
				statusText.setText("System state: " + " " + devices.size()
						+ " devices found");
				toggleContainer.removeAllViews();
			}
		});

		for (final String device : devices) {

			final boolean state = GAEAdapter.getInstance().getToogleState(
					device);

			final LinearLayout buttonContainer = new LinearLayout(Main.this);
			buttonContainer.setOrientation(LinearLayout.HORIZONTAL);

			final LinearLayout colorContainer = new LinearLayout(Main.this);
			colorContainer.setOrientation(LinearLayout.HORIZONTAL);
			colorContainer.setGravity( Gravity.CENTER_HORIZONTAL);
			colorContainer.setPadding( 0, 20, 0, 0);

			final ToggleButton button = new ToggleButton(Main.this);
			buttonContainer.addView(button);

			final TextView status = new TextView(Main.this);
			buttonContainer.addView(status);

			ColorPickerView colorPickerView = new ColorPickerView( toggleContainer.getContext(), this, 551155);
			colorContainer.addView(colorPickerView);

			runOnUiThread(new Runnable() {
				public void run() {
					button.setTextOn(device + " on");
					button.setTextOff(device + " off");
					button.setChecked(state);
					
					toggleContainer.addView(buttonContainer);
					toggleContainer.addView(colorContainer);
				}
			});

			button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					toggleState(device, !button.isChecked(), button, status);
				}
			});
			
			
			
		}
	}

	protected void toggleState(final String device, final boolean state,
			final ToggleButton button, final TextView status) {
		new Thread(new Runnable() {
			public void run() {
				GAEAdapter.getInstance().setToggleState(device, !state);
				runOnUiThread(new Runnable() {
					public void run() {
						status.setText("");

					}
				});
			}
		}).start();
		// TODO progress indicator
		status.setText(".. working .. ");

	}	
}
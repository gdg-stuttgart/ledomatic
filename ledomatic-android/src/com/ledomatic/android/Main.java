package com.ledomatic.android;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Main extends Activity {
    private Button toggleButton;
	private TextView statusText;
	private LinearLayout toogleContainer;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        toogleContainer = (LinearLayout) findViewById(R.id.toggleContainer);
        statusText = (TextView) findViewById(R.id.textStatus);
//        toggleButton = (Button) findViewById(R.id.toggleButton);
//        toggleButton.setOnClickListener(new OnClickListener()
//		{			
//			@Override
//			public void onClick(View v)
//			{
//				
//				toggleButton.setText(".. working ..");
//			}
//		});
    }
    
    
    @Override
    protected void onResume()
    {
    	
    	new Thread(new Runnable()
		{
			
			public void run()
			{
				final List<String> devices = GAEAdapter.getInstance().getDevices();
				Main.this.checkDevices(devices);
				
			}
		}).start();
    	
    	
    	

    	super.onResume();
    }


	protected void checkDevices(final List<String> devices)
	{
		runOnUiThread(new Runnable()
		{			
			public void run()
			{						
				statusText.setText( "System state: " + " " + devices.size() + " devices found");
			}
		});
		
		final LinearLayout container = new LinearLayout(Main.this);
		container.removeAllViews();
		
	
		for(final String device: devices)
		{
			
			final boolean state = GAEAdapter.getInstance().getToogleState(device) ;	
			container.setOrientation(LinearLayout.HORIZONTAL);
			final ToggleButton button = new ToggleButton(Main.this);
			final TextView status = new TextView(Main.this);
			container.addView(button);
			container.addView(status);
			runOnUiThread(new Runnable()
			{			
				public void run()
				{						
					button.setTextOn(device + " on");
					button.setTextOff( device + " off");
					button.setChecked(state);
					toogleContainer.addView(container);
//					toogleContainer.addView(indicator);
				}
			});
			
			button.setOnClickListener(new OnClickListener()
			{				
				public void onClick(View v)
				{					
					toggleState(device, !button.isChecked(), button, status);		
				}
			});
		}
		
		
	}


	protected void toggleState(final String device, final boolean state, final ToggleButton button, final TextView status)
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				GAEAdapter.getInstance().setToggleState(device, !state);			
				runOnUiThread(new Runnable()
				{					
					public void run()
					{
						status.setText("");			
						
					}
				});
			}
		}).start();
		// TODO progress indicator
		status.setText(".. working .. ");
		
		
	}
}
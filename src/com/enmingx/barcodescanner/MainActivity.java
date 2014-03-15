package com.enmingx.barcodescanner;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import android.view.Menu;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity implements OnClickListener {

	private Button scanButton;
	private TextView formatText, contentText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		scanButton = (Button)findViewById(R.id.scan_button);
		formatText = (TextView)findViewById(R.id.scan_format);
		contentText = (TextView)findViewById(R.id.scan_content);
		
		scanButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.scan_button) {
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanResult != null) {
			String scanContent = scanResult.getContents();
			String scanFormat = scanResult.getFormatName();
			contentText.setText("CONTENT: " + scanContent);
			formatText.setText("FORMAT: " + scanFormat);
		}
		else {
			Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}

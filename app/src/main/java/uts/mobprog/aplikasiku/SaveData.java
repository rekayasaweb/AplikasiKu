package uts.mobprog.aplikasiku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SaveData extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_trx);
		//get the inputed data
		Intent i = getIntent();
		Bundle b = i.getExtras();
		String custName = b.getString("custName");
		String custAddr = b.getString("custAddress");
		String custPhone = b.getString("custPhone");
		String custGender = b.getString("custGender");


		DBAdapter db = new DBAdapter(this);
		try {
			db.open();
			long id = db.insertCustomer(custName, custAddr, custGender, custPhone);
			Toast
					.makeText(this, "Data "+id+" successfully saved", Toast.LENGTH_SHORT)
					.show();
		}
		catch(Exception ex) {
			Toast
					.makeText(this, "Saving error data ", Toast.LENGTH_SHORT)
					.show();
		}
		finally {
			Button btnBack = (Button) findViewById(R.id.button1);
			btnBack.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent i = new Intent(SaveData.this, MainActivity.class);
					startActivity(i);
				}
			} );
		}
	}
}

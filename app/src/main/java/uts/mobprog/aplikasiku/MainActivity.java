package uts.mobprog.aplikasiku;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnNew = (Button) findViewById(R.id.button1);
		btnNew.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i= new Intent(MainActivity.this, trx_forms.class);
				startActivity(i);
			}
		} );
		Button btnBrowse = (Button) findViewById(R.id.button2);
		btnBrowse.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				DBAdapter db = new DBAdapter(MainActivity.this);
				db.open();
				Cursor c = db.getAllCustomers();
				if (c.moveToFirst()) {
					do {
						Toast.makeText(MainActivity.this, c.getString(1) + ", " + c.getString(2), Toast.LENGTH_SHORT).show();
					} while (c.moveToNext());
				}
				else
					Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_SHORT).show();
				db.close();
			}
		} );
	}
}

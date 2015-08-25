package uts.mobprog.aplikasiku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class trx_forms extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trx_form);
        Button Simpan = (Button) findViewById(R.id.Simpan);
        Simpan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//validate data entries
                String txt1 = ((EditText) findViewById(R.id.txt1)).getText().toString().trim();
                String txt2 = ((EditText) findViewById(R.id.txt2)).getText().toString().trim();
                String txt3 = ((EditText) findViewById(R.id.txt3)).getText().toString().trim();
                String txt4 = ((EditText) findViewById(R.id.txt4)).getText().toString().trim();

                Context context=trx_forms.this;
                {
                    if (txt1.equals("") || txt2.equals("") || txt3.equals("") || txt4.equals("")) {
                        String e = "Please complete the data.";
                        new AlertDialog.Builder(context)
                                .setTitle("Invalid Data")
                                .setMessage(e)
                                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
// TODO Auto-generated method stub
                                    }
                                }).show();
                    } else {
                        Intent i = new Intent(trx_forms.this, SaveData.class);
                        i.putExtra("custName", txt1);
                        i.putExtra("custAddress", txt2);
                        i.putExtra("custPhone", txt3);
                        i.putExtra("custGender", txt3);

                        startActivity(i);
                    }
                }

            }
        } );
    }
}
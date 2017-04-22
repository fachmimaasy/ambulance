package akash.com.ambulance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

import java.util.Map;

public class Loginsuccess extends AppCompatActivity {




    TextView username,name;


    DatabaseReference mDatabase;


    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuccess);

        username= (TextView) findViewById(R.id.success_username);
        name=(TextView) findViewById(R.id.success_name);

        Intent i= getIntent();

       // String userId=i.getStringExtra("userId");
        //Toast.makeText(Loginsuccess.this,"hi hello "+userId,Toast.LENGTH_SHORT).show();


        progress=new ProgressDialog(this);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

       String get_username= pref.getString("username",null);

        username.setText(get_username);

        name.setText(get_username);




    }



}

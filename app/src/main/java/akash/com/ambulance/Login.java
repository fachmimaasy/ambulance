package akash.com.ambulance;

import android.app.ProgressDialog;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {


    EditText username,pass;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        username=(EditText) findViewById(R.id.username);
        pass=(EditText) findViewById(R.id.password);

        mAuth=FirebaseAuth.getInstance();

        progress=new ProgressDialog(this);






    }

    public void loginCreate(View view){


        final String email=username.getText().toString();
        String password= pass.getText().toString();


        progress.setMessage("Loging in....");
        progress.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                            progress.dismiss();
                            Toast.makeText(Login.this, "failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else{


                            String userId=mAuth.getCurrentUser().getUid();



                            mDatabase= FirebaseDatabase.getInstance().getReference("user/"+userId);

                            // Toast.makeText(Loginsuccess.this,"hi  ",Toast.LENGTH_LONG).show();


                            progress.setMessage("Please Wait....");
                            progress.show();


                            mDatabase.addValueEventListener(new ValueEventListener() {



                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // This method is called once with the initial value and again
                                    // whenever data at this location is updated.


                                    String get_name = dataSnapshot.child("name").getValue(String.class);

                                   // name.setText(get_name);

                                    String get_email = dataSnapshot.child("email").getValue(String.class);

                                    //username.setText(get_email);

               /* for(DataSnapshot d:dataSnapshot.getChildren()){



                    Toast.makeText(Loginsuccess.this,"hi  "+d.getValue(String.class),Toast.LENGTH_LONG).show();

                }*/

                                    // Toast.makeText(Loginsuccess.this,"hi  "+map,Toast.LENGTH_LONG).show();


                                    SharedPreferences pref=getApplicationContext().getSharedPreferences("My",0);
                                    SharedPreferences.Editor editor=pref.edit();

                                    editor.putString("username","akash-karthick");
                                    editor.commit();




                                    progress.dismiss();
                                    Intent main=new Intent(Login.this,Loginsuccess.class);









                                    startActivity(main);





                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Toast.makeText(Login.this,"Falied to read",Toast.LENGTH_SHORT);
                                }
                            });










                        }


                        // ...
                    }
                });


    }




}

package com.example.all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDeleteSignup extends AppCompatActivity {


    EditText ufnamez,uaddrez,uemailz,ucontact,upass,uconpass;

    FirebaseAuth fAuth;
    Button uregBtn,dregBtn;

    DatabaseReference dbRef;
    Signupclass signupclass;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_delete_signup);

        ufnamez=findViewById(R.id.upds1);
        uaddrez=findViewById(R.id.upds2);
        uemailz=findViewById(R.id.upds3);
        ucontact=findViewById(R.id.upds4);
        upass=findViewById(R.id.upds5);

        //  progressBar=findViewById(R.id.progressBar2);

        signupclass=new Signupclass();


        uregBtn=findViewById(R.id.uusignupup);
        dregBtn=findViewById(R.id.updssbuttonsu);

        fAuth=FirebaseAuth.getInstance();
        String uid =fAuth.getCurrentUser().getUid();

        //db
     //     dbRef= FirebaseDatabase.getInstance().getReference().child("Register");


//show
        DatabaseReference readRef1 = FirebaseDatabase.getInstance().getReference().child("Register").child(uid);
        readRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    ufnamez.setText(dataSnapshot.child("suname").getValue().toString());
                    uaddrez.setText(dataSnapshot.child("suaddress").getValue().toString());
                    uemailz.setText(dataSnapshot.child("suemail").getValue().toString());
                    ucontact.setText(dataSnapshot.child("suphone").getValue().toString());
                    upass.setText(dataSnapshot.child("supassw").getValue().toString());



                }
                else
                    Toast.makeText(getApplicationContext(),"No Source to Display",
                            Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //




        //update


        //////////////////update


        uregBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Register");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        fAuth=FirebaseAuth.getInstance();
                        String uid =fAuth.getCurrentUser().getUid();

                        if(dataSnapshot.hasChild(uid)) {

                            try {
                                signupclass.setId(fAuth.getCurrentUser().getUid());
                                signupclass.setSuname(ufnamez.getText().toString().trim());
                                signupclass.setSuaddress(uaddrez.getText().toString().trim());
                                signupclass.setSuemail(uemailz.getText().toString().trim());
                                signupclass.setSuphone(Integer.parseInt(ucontact.getText().toString().trim()));
                                signupclass.setSupassw(upass.getText().toString().trim());




                                dbRef = FirebaseDatabase.getInstance().getReference().child("Register").child(uid);
                                dbRef.setValue(signupclass);
                                clearControls();

                                Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(),"Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                            Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }


        });


//////////////DELETE

        dregBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth=FirebaseAuth.getInstance();
                String uid =fAuth.getCurrentUser().getUid();

                dbRef = FirebaseDatabase.getInstance().getReference().child("Register").child(uid);
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(),"sucessfully deleted",Toast.LENGTH_SHORT).show();


                Intent packageContent = null;
                Intent intent1= new Intent(UpdateDeleteSignup.this,Signup.class);
                startActivity(intent1);

            }
        });

    }



    private void clearControls() {
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Register");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {

                    ufnamez.setText(dataSnapshot.child("suname:").getValue().toString());
                    uaddrez.setText(dataSnapshot.child("suaddress").getValue().toString());
                    uemailz.setText(dataSnapshot.child("suemail").getValue().toString());
                    ucontact.setText(dataSnapshot.child("suphone").getValue().toString());
                    upass.setText(dataSnapshot.child("supassw").getValue().toString());

                }
                else
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    ///////////////

    private String getId() {
       return (fAuth.getCurrentUser().getUid());
    }



}
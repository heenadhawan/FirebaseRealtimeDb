package com.android.firebasedbstorage;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.UriPermission;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
   EditText name,email,password,confirmpassword,phone;
   Button submit,up,ch;
   ImageView img;
   DatabaseReference reff;
   Member member;
   long maxid=0;
   StorageReference sreff;
   private StorageTask uploadtask;
   public Uri imguri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =(EditText)findViewById(R.id.name);
        email =(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.pasword);
        confirmpassword=(EditText)findViewById(R.id.confirmpass);
        submit= (Button)findViewById(R.id.button);
       /* ch= (Button)findViewById(R.id.choose);
        img =(ImageView)findViewById(R.id.imageView2) ;*/
//        up= (Button)findViewById(R.id.upload);
//        up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(uploadtask != null && uploadtask.isInProgress()){
//                    Toast.makeText(MainActivity.this,
//                            "Upload in progress",Toast.LENGTH_LONG).show();
//                }else{
//                FileUploader();
//                }
//            }
//        });
      /*  ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FileChooser();
            }
        });*/
        member = new Member();
        sreff = FirebaseStorage.getInstance().getReference("images");
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists());
                maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long ph =Long.parseLong(phone.getText().toString().trim());
                member.setName(name.getText().toString().trim());
                member.setEmail(email.getText().toString().trim());
                member.setPass(password.getText().toString().trim());
                member.setConfirmpass(confirmpassword.getText().
                        toString().trim());
               member.setPhone(ph);
               reff.push().setValue(member);
                reff.child(String.valueOf(maxid+1)).setValue(member);
                Toast.makeText(MainActivity.this,
                        "Imageinsertsuccessful",Toast.LENGTH_LONG).show();
            }
        });
    }

   /* private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }*/

 /*   private void FileUploader () {
        String imgid;
      *//*  imgid=System.currentTimeMillis()+"."+getExtension(imguri);*//*
            long ph =Long.parseLong(phone.getText().toString().trim());
            member.setName(name.getText().toString().trim());
            member.setEmail(email.getText().toString().trim());
            member.setPass(password.getText().toString().trim());
            member.setImageid(imgid);
            member.setConfirmpass(confirmpassword.getText().
                    toString().trim());
            member.setPhone(ph);
            reff.push().setValue(member);
            StorageReference Ref= sreff.child(imgid);
            uploadtask=Ref.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Toast.makeText(MainActivity.this,
                                    "datainsertsuccessful",Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });
        }

    private void FileChooser() {
        Intent INTENT = new Intent();
        INTENT.setType("image/");
        INTENT.setAction(Intent.ACTION_GET_CONTENT);
       startActivityForResult(INTENT,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&& requestCode==RESULT_OK && data!=null&&data.getData()!=null)
        {
           imguri = data.getData();
           img.setImageURI(imguri);
        }
    }*/
}

package com.example.tabty.utilities;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseManagement {

   public static void loginIntoFirebase(String email , String password, FirebaseCallback firebaseCallbak)
    {
        FirebaseAuth myFirebase = FirebaseAuth.getInstance();
        myFirebase.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    firebaseCallbak.onFirebaseResponse(true,null);
//                    View view = new View(context);
//                    Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
//                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_onBoardFragment);
                }else{
                    //Toast.makeText(context, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    firebaseCallbak.onFirebaseResponse(false,task.getException().getLocalizedMessage());
                }
            }
        });
    }

    public static void createFirebaseAccount(String email, String password , FirebaseCallback firebaseCallbak)
    {
        FirebaseAuth myFirebase = FirebaseAuth.getInstance();
        myFirebase.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            firebaseCallbak.onFirebaseResponse(true,null);

                        }
                        else{
                            firebaseCallbak.onFirebaseResponse(false,task.getException().getLocalizedMessage());
                        }
                    }
                });
    }

    public static void logoutFromFirebase()
    {
        FirebaseAuth.getInstance().signOut();
    }
}

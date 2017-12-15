package com.lubnasweety.pricehero.backEnd;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Asus on 11/23/2017.
 */

public class DataHelper {
    private static final DataHelper instance = new DataHelper();
    FirebaseDatabase mFirebseDatabase;
    DatabaseReference database;
    FirebaseAuth mFirebaseAuth;
    FirebaseStorage mFirebaseStorage;
    StorageReference storage;

    public static DataHelper getInstance() {
        return instance;
    }

    public Task<AuthResult> signIn(String email, String password) {
        return mFirebaseAuth.signInWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> signUp(String email, String password) {
        return mFirebaseAuth.createUserWithEmailAndPassword(email, password);
    }

    public void logOut() {
        mFirebaseAuth.signOut();
    }



    private DataHelper() {
        mFirebseDatabase = FirebaseDatabase.getInstance();
        database = mFirebseDatabase.getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        storage = mFirebaseStorage.getReference();
    }

    public FirebaseUser getUser() {
        return mFirebaseAuth.getCurrentUser();

    }
}
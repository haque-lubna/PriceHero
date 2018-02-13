package com.lubnasweety.pricehero.backEnd;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by Asus on 11/23/2017.
 */

public class DataHelper {
    private static final DataHelper instance = new DataHelper();
    private FirebaseDatabase mFirebseDatabase;
    private DatabaseReference database;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference storage;

    public DatabaseReference getDatabase() {
        return database;
    }

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


    public UploadTask uploadImage(Uri uri) {
        String currentTime = String.valueOf(System.currentTimeMillis());
        return storage.child("images").child(currentTime).child(uri.getLastPathSegment()).putFile(uri);
    }

    public String getUid() {
        return mFirebaseAuth.getCurrentUser().getUid();
    }

    public void pushProduct(String productName, String productCategory, String productDescription, String storeName, String storeLocation, String productPrice, String productQuantity, String productOffers, String imagePath,LatLng bLatlng) {
        DatabaseReference product = database.child("products").child(productCategory).child(productName);
        product.child("name").setValue(productName);
        product.child("image").setValue(imagePath);
        product.child("category").setValue(productCategory);

        Shop shop = new Shop(productDescription, storeName, storeLocation, productQuantity, productOffers, Double.parseDouble(productPrice), getUid(), imagePath,bLatlng);
        product.child("shops").push().setValue(shop);
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
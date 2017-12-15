package com.lubnasweety.pricehero;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;
import com.lubnasweety.pricehero.backEnd.DataHelper;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    static final int RC_PHOTO_PICKER = 1;

    DataHelper dataHelper;
    EditText productName;
    EditText productCategory;
    EditText productDescription;
    EditText storeName;
    EditText storeLocation;
    EditText productPrice;
    EditText productQuantity;
    EditText productOffers;
    TextView imageName;
    Button btnChooseImage;
    Button btnSubmit;

    View v;
    Uri selectedImageUri;
    ProgressDialog dialog;

    //store them in database
    String imagePath;
    String productNameText;
    String productCategoryText;
    String productDescriptionText;
    String storeNameText;
    String storeLocationText;
    String productPriceText;
    String productQuantityText;
    String productOffersText;


    private OnFragmentInteractionListener mListener;

    public PostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        dataHelper = DataHelper.getInstance();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_post, container, false);

        productName = (EditText) v.findViewById(R.id.productName);
        productCategory = (EditText) v.findViewById(R.id.productCategory);
        productDescription = (EditText) v.findViewById(R.id.productDescription);
        storeName = (EditText) v.findViewById(R.id.storeName);
        storeLocation = (EditText) v.findViewById(R.id.storeLocation);
        productPrice  = (EditText) v.findViewById(R.id.productPrice);
        productQuantity = (EditText) v.findViewById(R.id.productQuantity);
        productOffers = (EditText) v.findViewById(R.id.productOffers);
        imageName = (TextView) v.findViewById(R.id.imageName);
        btnChooseImage = (Button) v.findViewById(R.id.btnChooseImage);
        btnSubmit = (Button) v.findViewById(R.id.btnSubmit);

        btnChooseImage.setOnClickListener(e->{
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/jpeg");
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
        });

        btnSubmit.setOnClickListener(e->{
            productNameText = productName.getText().toString();
            productCategoryText = productCategory.getText().toString();
            productDescriptionText = productDescription.getText().toString();
            storeNameText = storeName.getText().toString();
            storeLocationText = storeLocation.getText().toString();
            productQuantityText = productQuantity.getText().toString();
            productOffersText = productOffers.getText().toString();

            dialog = new ProgressDialog(getActivity());
            dialog.setTitle("Uploading Image...");
            dialog.show();
            UploadTask uploading = dataHelper.uploadImage(selectedImageUri);
            uploading.addOnFailureListener(t->{
                dialog.cancel();
                Toast.makeText(getActivity(), "Uploading failed...", Toast.LENGTH_LONG).show();
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagePath = taskSnapshot.getDownloadUrl().toString();
                    dialog.setTitle("Uploading product info...");
                    dataHelper.pushProduct(productNameText, productCategoryText, productDescriptionText, storeNameText, storeLocationText, productPriceText, productQuantityText, productOffersText, imagePath);
                    dialog.cancel();
                    Toast.makeText(getActivity(), "Product added...", Toast.LENGTH_LONG).show();
                }
            });
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RC_PHOTO_PICKER) {
            if(resultCode==RESULT_OK) {
                selectedImageUri = data.getData();
                imageName.setText(selectedImageUri.getLastPathSegment());

            } else if(resultCode==RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Image Choosing Failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Toast.makeText(context, "Post Fragment Attached", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}


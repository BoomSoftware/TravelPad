package com.example.travelpad.views.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.travelpad.HomeAppActivity;
import com.example.travelpad.R;
import com.example.travelpad.viewmodels.login.SignInViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInFragment extends Fragment {

    private final int RC_SIGN_IN = 1;

    private SignInViewModel viewModel;
    private View view;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    private Button confirmSignInButton;
    private SignInButton googleSignInButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView goToSignUpTextView;

    public SignInFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(view.getContext(), gso);
//        mAuth = FirebaseAuth.getInstance();
//
//        prepareUI(view);
//        prepareOnClickEvents(view);

        return view;
    }

//    private void prepareUI(View view) {
//        confirmSignInButton = view.findViewById(R.id.button_ConfirmSignIn);
//        goToSignUpTextView = view.findViewById(R.id.text_GoToSignUp);
//        emailEditText = view.findViewById(R.id.edit_SignInEmail);
//        passwordEditText = view.findViewById(R.id.edit_SignInPassword);
//        googleSignInButton = view.findViewById(R.id.button_google_sign_in);
//    }
//
//    private void prepareOnClickEvents(View view){
//        goToSignUpTextView.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.goToSignUp));
//        confirmSignInButton.setOnClickListener(v ->{
//            boolean result = viewModel.signIn(emailEditText.getText().toString(), passwordEditText.getText().toString());
////            if(result){
////                Intent intent = new Intent(view.getContext(), HomeAppActivity.class);
////                view.getContext().startActivity(intent);
////            }
////            Toast.makeText(getContext(), "WRONG DATA", Toast.LENGTH_SHORT).show();
//        });
//
//        googleSignInButton.setOnClickListener(v -> {
//            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//            startActivityForResult(signInIntent, RC_SIGN_IN);
//        });
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                Log.d("LOGGIN MASSAGE",account.getId() + "");
//                firebaseAuthWithGoogle(account.getIdToken());
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//                Log.w("LOGGIN MASSAGE", "Google sign in failed", e);
//            }
//        }
//    }
//
//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("LOGGIN MASSAGE", "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Intent intent = new Intent(view.getContext(), HomeAppActivity.class);
//                            view.getContext().startActivity(intent);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("LOGGIN MASSAGE", "signInWithCredential:failure", task.getException());
//                        }
//                    }
//                });
//    }

}
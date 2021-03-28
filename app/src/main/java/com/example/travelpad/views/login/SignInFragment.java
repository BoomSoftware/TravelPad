package com.example.travelpad.views.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelpad.HomeAppActivity;
import com.example.travelpad.MainActivity;
import com.example.travelpad.R;
import com.example.travelpad.viewmodels.SignInViewModel;

public class SignInFragment extends Fragment {

    private SignInViewModel viewModel;

    private View view;
    private Button confirmSignInButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView goToSignUpTextView;

    public SignInFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        prepareUI(view);
        prepareOnClickEvents(view);

        return view;
    }

    private void prepareUI(View view) {
        confirmSignInButton = view.findViewById(R.id.button_ConfirmSignIn);
        goToSignUpTextView = view.findViewById(R.id.text_GoToSignUp);
        emailEditText = view.findViewById(R.id.edit_SignInEmail);
        passwordEditText = view.findViewById(R.id.edit_SignInPassword);
    }

    private void prepareOnClickEvents(View view){
        goToSignUpTextView.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.goToSignUp));
        confirmSignInButton.setOnClickListener(v ->{
            boolean result = viewModel.signIn(emailEditText.getText().toString(), passwordEditText.getText().toString());
            if(result){
                Intent intent = new Intent(view.getContext(), HomeAppActivity.class);
                view.getContext().startActivity(intent);
            }
            Toast.makeText(getContext(), "WRONG DATA", Toast.LENGTH_SHORT).show();
        });
    }
}
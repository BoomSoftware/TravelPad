package com.example.travelpad.views.login;

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
import android.widget.Toast;

import com.example.travelpad.R;
import com.example.travelpad.viewmodels.login.SignUpViewModel;

public class SignUpFragment extends Fragment {

    private SignUpViewModel viewModel;

    private View view;
    private ImageButton backButton;
    private Button confirmSignIn;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    public SignUpFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);

//        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
//
//        prepareUI(view);
//        prepareOnClickEvents(view);
//        prepareObservable();

        return view;
    }

//    private void prepareUI(View view) {
//        backButton = view.findViewById(R.id.button_SignUpBack);
//        emailEditText = view.findViewById(R.id.edit_SignUpEmail);
//        passwordEditText = view.findViewById(R.id.edit_SignUpPassword);
//        confirmPasswordEditText = view.findViewById(R.id.edit_SignUpPasswordConfirm);
//        confirmSignIn = view.findViewById(R.id.button_ConfirmSignUp);
//    }
//
//    private void prepareOnClickEvents(View view){
//        backButton.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.gotToSignIn));
//        confirmSignIn.setOnClickListener(v->{
//            boolean result = viewModel.signUp(emailEditText.getText().toString(), passwordEditText.getText().toString(), confirmPasswordEditText.getText().toString());
//            if(result){
//                Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
//                return;
//            }
//            Toast.makeText(getContext(), "WRONG DATA", Toast.LENGTH_SHORT).show();
//        });
//    }
//
//    private void prepareObservable(){
//
//    }
}
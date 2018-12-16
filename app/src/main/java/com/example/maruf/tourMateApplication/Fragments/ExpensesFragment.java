package com.example.maruf.tourMateApplication.Fragments;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maruf.tourMateApplication.ProjoPackage.Expenses;
import com.example.maruf.tourMateApplication.R;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class ExpensesFragment extends Fragment {


public ExpensesFragment() {
    // Required empty public constructor
}

private FirebaseAuth firebaseAuth;
private String userId;
private BottomSheetDialog bottomSheetDialog;
private FloatingActionButton openBottomsheetExpenseBtn;
private EditText details,amount;
private  Button expenseBtn;
@Override
public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                         Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_expenses, container, false);
    Bundle bundle = getArguments();
    String eventID = bundle.getString("id");
    firebaseAuth = FirebaseAuth.getInstance();
    userId = firebaseAuth.getCurrentUser().getUid();
    openBottomsheetExpenseBtn = view.findViewById(R.id.openBottomSheetExpense);
    openBottomsheetExpenseBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bottomSheetDialog = new BottomSheetDialog(getActivity());
            View sheetview = getLayoutInflater().inflate(R.layout.bottom_sheet_expense,null);
            bottomSheetDialog.setContentView(sheetview);
            bottomSheetDialog.show();
            details = sheetview.findViewById(R.id.expenseDetailET);
            amount = sheetview.findViewById(R.id.expenseAmountET);
            expenseBtn = sheetview.findViewById(R.id.addExpense);
            expenseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateAndSend();
                }
            });



        }
    });










    return view;

}

    private void validateAndSend() {


    }


}

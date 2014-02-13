package com.eleks.espresso.example.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment
{

	public static final String TEXT = "text";
	public static final String TAG = CustomDialogFragment.class.getSimpleName();

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		String text = getArguments().getString(TEXT);
		AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("Value").setMessage(text).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{

			}
		}).create();
		return dialog;
	}
}

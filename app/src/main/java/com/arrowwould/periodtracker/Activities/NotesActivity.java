package com.arrowwould.periodtracker.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.arrowwould.periodtracker.Adapters.DiaryNotesRecyclerAdapter;
import com.arrowwould.periodtracker.Databases.Entities.Note;
import com.arrowwould.periodtracker.Databases.NoteHandler;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.Utils.Utils;
import com.arrowwould.periodtracker.databinding.ActivityNotesBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


public class NotesActivity extends AppCompatActivity implements DiaryNotesRecyclerAdapter.OnLongItemCLickedListener {
    ActivityNotesBinding binding;
    NoteHandler handler;
    boolean savedNotes = false;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityNotesBinding inflate = ActivityNotesBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());

//        AdsGoogle adsGoogle = new AdsGoogle( this);
//        adsGoogle.Banner_Show((RelativeLayout) findViewById(R.id.banner), this);
//        adsGoogle.Interstitial_Show_Counter(this);

        setCurrentDate();
        this.binding.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                NotesActivity.this.m112x3d0b51f4(view);
            }
        });
        this.binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                NotesActivity.this.m113x261316f5(view);
            }
        });
        showNotesList();
        this.binding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                NotesActivity.this.m114xf1adbf6(view);
            }
        });
        this.binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                NotesActivity.this.m115xf822a0f7(view);
            }
        });
        this.binding.dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                NotesActivity.this.m116xe12a65f8(view);
            }
        });
    }


    public void m112x3d0b51f4(View view) {
        Utils.hideKeyboard(this);
    }


    public void m113x261316f5(View view) {
        Utils.hideKeyboard(this);
        if (this.binding.dairyNoteInp.getText().toString().isEmpty()) {
            this.binding.dairyNoteInp.setError(getResources().getString(R.string.please_fill_all_fields));
        } else {
            addNote();
        }
    }


    public void m114xf1adbf6(View view) {
        Utils.hideKeyboard(this);
        showNotesList();
    }


    public void m115xf822a0f7(View view) {
        this.binding.inputArea.setVisibility(View.VISIBLE);
        this.binding.detailArea.setVisibility(View.GONE);
        this.binding.addBtn.setVisibility(View.GONE);
    }


    public void m116xe12a65f8(View view) {
        String[] split = this.binding.dateTv.getText().toString().split("/");
        String trim = split[0].trim();
        String trim2 = split[1].trim();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, 16973940, this.setListener, Integer.parseInt(split[2].trim()), Integer.parseInt(trim2) - 1, Integer.parseInt(trim));
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        datePickerDialog.show();
    }

    void setCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(5);
        this.binding.dateTv.setText(i2 + "/" + (calendar.get(2) + 1) + "/" + i);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.handler = new NoteHandler(this);
        showNotesList();
    }

    private void addNote() {
        this.handler.addNote(new Note(this.binding.dateTv.getText().toString(), this.binding.dairyNoteInp.getText().toString()));
        this.binding.inputArea.setVisibility(View.GONE);
        this.binding.detailArea.setVisibility(View.VISIBLE);
        this.binding.addBtn.setVisibility(View.VISIBLE);
        this.binding.dairyNoteInp.setText("");
        showNotesList();
        Toast.makeText(this, getResources().getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
        this.savedNotes = true;
        Utils.hideKeyboard(this);
    }

    private void showNotesList() {
        this.setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                NotesActivity.this.m118x1de85726(datePicker, i, i2, i3);
            }
        };
        List<Note> arrayList = new ArrayList<>();
        NoteHandler noteHandler = this.handler;
        if (noteHandler != null) {
            arrayList = noteHandler.getAllNotes();
        }
        if (arrayList.size() > 0) {
            this.binding.diaryNotesRecycler.setLayoutManager(new LinearLayoutManager(this));
            Collections.reverse(arrayList);
            this.binding.diaryNotesRecycler.setAdapter(new DiaryNotesRecyclerAdapter(arrayList, this, this));
            this.binding.diaryNotesRecycler.scrollToPosition(0);
            this.binding.diaryNotesRecycler.setVisibility(View.VISIBLE);
            this.binding.emptyTv.setVisibility(View.GONE);
            this.binding.inputArea.setVisibility(View.GONE);
            this.binding.detailArea.setVisibility(View.VISIBLE);
            this.binding.addBtn.setVisibility(View.VISIBLE);
            return;
        }
        this.binding.addBtn.setVisibility(View.GONE);
        this.binding.detailArea.setVisibility(View.GONE);
        this.binding.inputArea.setVisibility(View.VISIBLE);
        this.binding.emptyTv.setVisibility(View.VISIBLE);
        this.binding.diaryNotesRecycler.setVisibility(View.GONE);
    }


    public void m118x1de85726(DatePicker datePicker, int i, int i2, int i3) {
        int i4 = i2 + 1;
        this.binding.dateTv.setText(i3 + "/" + i4 + "/" + i);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i4 + (-1));
        calendar.set(5, i3);
    }

    @Override
    public void onItemLongClicked(final int i) {
        new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.delete_confirmation)).setMessage(getResources().getString(R.string.are_you_sure_you_want_to_delete_this_history)).setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public final void onClick(DialogInterface dialogInterface, int i2) {
                NotesActivity.this.m117xbca8c07e(i, dialogInterface, i2);
            }
        }).setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public final void onClick(DialogInterface dialogInterface, int i2) {
                NotesActivity.lambda$onItemLongClicked$7(dialogInterface, i2);
            }
        }).show();
    }


    public void m117xbca8c07e(int i, DialogInterface dialogInterface, int i2) {
        this.handler.deleteNotes(String.valueOf(i));
        showNotesList();
        Toast.makeText(this, getResources().getString(R.string.deleted_successfully), Toast.LENGTH_SHORT).show();
    }

    public static void lambda$onItemLongClicked$7(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}

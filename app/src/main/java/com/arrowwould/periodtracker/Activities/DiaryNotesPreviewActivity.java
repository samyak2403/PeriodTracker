package com.arrowwould.periodtracker.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.arrowwould.periodtracker.Databases.Entities.Note;
import com.arrowwould.periodtracker.Databases.NoteHandler;
import com.arrowwould.periodtracker.Databases.Params;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.databinding.ActivityDiaryNotesPreviewBinding;


public class DiaryNotesPreviewActivity extends AppCompatActivity {
    ActivityDiaryNotesPreviewBinding binding;
    NoteHandler handler;
    Note singleNote;

    
    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityDiaryNotesPreviewBinding inflate = ActivityDiaryNotesPreviewBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
//        AdsGoogle adsGoogle = new AdsGoogle( this);
//        adsGoogle.Banner_Show((RelativeLayout) findViewById(R.id.banner), this);
//        adsGoogle.Interstitial_Show_Counter(this);

        final int intExtra = getIntent().getIntExtra(Params.KEY_NOTE_ID, 0);
        this.handler = new NoteHandler(this);
        this.binding.backBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                DiaryNotesPreviewActivity.this.m104x3a52915d(view);
            }
        });
        if (intExtra != 0) {
            this.singleNote = this.handler.getNoteById(intExtra);
            this.binding.dateTv.setText(this.singleNote.getDate());
            this.binding.notesDescTv.setText(this.singleNote.getNote());
            this.binding.dairyNoteInp.setText(this.singleNote.getNote());
            this.binding.deleteIcon.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public final void onClick(View view) {
                    DiaryNotesPreviewActivity.this.m106x457473e0(intExtra, view);
                }
            });
            this.binding.editIcon.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public final void onClick(View view) {
                    DiaryNotesPreviewActivity.this.m107x9e7fbf61(view);
                }
            });
            this.binding.cancelBtn.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public final void onClick(View view) {
                    DiaryNotesPreviewActivity.this.m108xf78b0ae2(view);
                }
            });
            this.binding.saveBtn.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public final void onClick(View view) {
                    DiaryNotesPreviewActivity.this.m109x50965663(intExtra, view);
                }
            });
        }
    }

    
    
    public  void m104x3a52915d(View view) {
        onBackPressed();
    }

    
    
    public  void m106x457473e0(final int i, View view) {
        new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.delete_confirmation)).setMessage(getResources().getString(R.string.are_you_sure_you_want_to_delete_this_history)).setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() { 
            @Override 
            public final void onClick(DialogInterface dialogInterface, int i2) {
                DiaryNotesPreviewActivity.this.m105x935ddcde(i, dialogInterface, i2);
            }
        }).setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() { 
            @Override 
            public final void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    
    
    public  void m105x935ddcde(int i, DialogInterface dialogInterface, int i2) {
        this.handler.deleteNotes(String.valueOf(i));
        onBackPressed();
        Toast.makeText(this, getResources().getString(R.string.deleted_successfully), Toast.LENGTH_SHORT).show();
    }

    
    
    public  void m107x9e7fbf61(View view) {
        this.binding.notesDescTv.setVisibility(View.GONE);
        this.binding.editIcon.setVisibility(View.GONE);
        this.binding.inputArea.setVisibility(View.VISIBLE);
    }

    
    
    public  void m108xf78b0ae2(View view) {
        this.binding.notesDescTv.setVisibility(View.VISIBLE);
        this.binding.editIcon.setVisibility(View.VISIBLE);
        this.binding.inputArea.setVisibility(View.GONE);
    }

    
    
    public  void m109x50965663(int i, View view) {
        this.singleNote.setNote(this.binding.dairyNoteInp.getText().toString());
        this.handler.updateNotes(this.singleNote, String.valueOf(i));
        Toast.makeText(this, getResources().getString(R.string.updated_successfully), Toast.LENGTH_SHORT).show();
        finish();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBackPressed() {
        if (this.binding.inputArea.getVisibility() == View.VISIBLE) {
            new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.are_you_sure_you_want_to_exit)).setIcon(17301642).setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() { 
                @Override 
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DiaryNotesPreviewActivity.this.m103x7488986b(dialogInterface, i);
                }
            }).setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() { 
                @Override 
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        } else {
            super.onBackPressed();
        }
    }

    
    
    public  void m103x7488986b(DialogInterface dialogInterface, int i) {
        super.onBackPressed();
    }
}

package sda.internaldatabasecontacts;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends Activity {

    private EditText editName, editSurname, editPhone;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        editName = (EditText) findViewById(R.id.editName);
        editSurname = (EditText) findViewById(R.id.editSurname);
        editPhone = (EditText) findViewById(R.id.editPhone);
        saveButton = (Button) findViewById(R.id.buttonSave);

        if (getIntent().hasExtra("contact")) {
            Contact contact = (Contact) getIntent().getParcelableExtra("contact");
            editName.setText(contact.getFirstName());
            editSurname.setText(contact.getSurname());
            editPhone.setText(contact.getPhoneNumber());
            editedID = contact.getId();
        }
    }

    @BindView(R.id.editName)
    private EditText editName;
    @BindView(R.id.editSurname)
    private EditText editSurname;
    @BindView(R.id.editPhone)
    private EditText editPhone;
    @BindView(R.id.buttonSave)
    private Button saveButton;
    private int editedID;


    @Override
    public void onClick(View v) {
        Contact newContact = new Contact();

        newContact.setFirstName(editName.getText().toString());
        newContact.setSurname(editSurname.getText().toString());
        newContact.setPhoneNumber(editPhone.getText().toString());
        if (getIntent().hasExtra("contact")) {
            newContact.setId(editedID);
            new ContactsDatabaseHelper(getApplicationContext()).updateContact(newContact);
        } else {
            new ContactsDatabaseHelper(getApplicationContext()).createContact(newContact);
        }
        finish();

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PopupWindow window = createPopup(position);
                window.showAsDropDown(view, 0, 0);
                return true;
            }
        });

        public PopupWindow createPopup(final int position) {
            final PopupWindow window = new PopupWindow(this);
            Button newButton = new Button(this);
            newButton.setText("Delete");
            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.deleteContact(array.getItem(position));
                    array.remove(array.getItem(position));
                    window.dismiss();
                }
            });
            window.setFocusable(true);
            window.setContentView(newButton);
            window.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
            return window;
        }
    }

        @OnClick(R.id.buttonSave)
        public void onSaveClick(View v) {
            Contact newContact = new Contact();
            newContact.setFirstName(editName.getText().toString());
            newContact.setSurname(editSurname.getText().toString());
            newContact.setPhoneNumber(editPhone.getText().toString());
            new ContactsDatabaseHelper(getApplicationContext()).createContact(newContact);
            finish();
        }
    public List<Contact> readContacts() { // read
        List<Contact> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor coursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        for (int i = 0; i < coursor.getCount(); i++) {
            coursor.moveToNext();
            Contact newContact = new Contact();
            newContact.setId(coursor.getInt(0));
            newContact.setFirstName(coursor.getString(1));
            newContact.setSurname(coursor.getString(2));
            newContact.setPhoneNumber(coursor.getString(3));
            list.add(newContact);
        }
        return list;
    }

    public SQLiteDatabase getReadableDatabase() {
        return readableDatabase;
    }
}

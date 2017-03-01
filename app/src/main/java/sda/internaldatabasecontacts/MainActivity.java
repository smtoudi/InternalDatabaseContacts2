package sda.internaldatabasecontacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class MainActivity extends Activity {

    private ListView list;
    private ArrayAdapter<Contact> array;
    private ContactsDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dbHelper = new ContactsDatabaseHelper(getApplicationContext());
        list = (ListView) findViewById(R.id.list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("contact", array.getItem(position));
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_contact) {
            Intent i = new Intent(this, SecondActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.list)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        i.putExtra("contact", array.getItem(position));
        startActivity(i);
    }
    @OnLongClick(R.id.list)
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        PopupWindow window = createPopup(position);
        window.showAsDropDown(view, 0, 0);
        return true;
    }

    private void updateList() {
        array = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, dbHelper.readContacts());
        list.setAdapter(array);
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }


}

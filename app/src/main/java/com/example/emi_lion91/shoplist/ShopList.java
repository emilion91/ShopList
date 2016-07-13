package com.example.emi_lion91.shoplist;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.emi_lion91.shoplist.DatabaseOperations.DBOperations;
import com.example.emi_lion91.shoplist.DatabaseOperations.ListContent;

import java.util.List;

public class ShopList extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    Context context = this;


    @Override
    public void onListFragmentInteraction(ListContent.DummyItem item) {

    }


    @Override
    public void onDeleteButtonPress(ListContent.DummyItem mItem) {
        ListContent.deleteItem(mItem);
        ((RecyclerView) findViewById(R.id.list)).getAdapter().notifyDataSetChanged();

        DBOperations dbo = new DBOperations(context);
        dbo.deleteItem(dbo, mItem.content);
        Toast.makeText(getBaseContext(), "Item deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(ListContent.ITEMS.isEmpty()) {
            DBOperations dbo = new DBOperations(context);
            if (dbo.checkDB(context)) {
                Cursor cr = dbo.getInfo(dbo);
                while (cr.moveToNext()) {
                    ListContent.addItem(new ListContent.DummyItem(null, cr.getString(0), null));
                }
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_container, ItemFragment.newInstance(1), "ListFragment");
        ft.commit();

        Button add = (Button)findViewById(R.id.item_add);
        assert add != null;
        if (add != null) {
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText item = (EditText)findViewById(R.id.added_item);
                    if(!item.getText().toString().equals("")) {
                        ListContent.addItem(new ListContent.DummyItem(item.getText().toString(), item.getText().toString(), null));
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                        recyclerView.getAdapter().notifyDataSetChanged();
                        DBOperations dbo = new DBOperations(context);
                        dbo.putInfo(dbo, item.getText().toString());
                        Toast.makeText(getBaseContext(), "Item Added", Toast.LENGTH_SHORT).show();
                        item.setText("");
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Tap \"Grocery Item\" to input your item", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

/**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        List<ListContent.DummyItem> items = ListContent.ITEMS;
        for (fragment_item fragment: items) {
            //noinspection SimplifiableIfStatement
            if (id == R.id.display) {
                CheckBox myCheckbox = (CheckBox) findViewById(R.id.myCheckbox);
                myCheckbox.setVisibility(View.VISIBLE);
                return true;
            } else if (id == R.id.erase) {
                CheckBox myCheckbox = (CheckBox) findViewById(R.id.myCheckbox);
                myCheckbox.setVisibility(View.INVISIBLE);
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
 **/
}

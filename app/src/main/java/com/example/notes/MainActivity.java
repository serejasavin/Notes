package com.example.notes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView Result;
    private Button Create_Button;
    private ListView Shopping_List;
    private EditText Name, Price;
    private SQLiteDatabase DB;
    private List<Item> Items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Result = findViewById(R.id.Result);
        Create_Button = findViewById(R.id.Create_Button);
        Shopping_List = findViewById(R.id.Shopping_List);
        Name = findViewById(R.id.Name);
        Price = findViewById(R.id.Price);
        final DBHelper dbHelper = new DBHelper(this);
        DB = dbHelper.getWritableDatabase();
        getItems();
        final SimpleAdapter simpleAdapter = new SimpleAdapter(Items);
        Shopping_List.setAdapter(simpleAdapter);
        Create_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Name.getText().toString().isEmpty() || Price.getText().toString().isEmpty())
                    return;
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.COLUMN_NAME, Name.getText().toString());
                contentValues.put(DBHelper.COLUMN_PRICE, Price.getText().toString());
                DB.insert(DBHelper.TABLE, null, contentValues);
                getItems();
                simpleAdapter.notifyDataSetChanged();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void getItems() {
        Items.clear();
        Cursor cursor = DB.query(
                DBHelper.TABLE,
                new String[]{DBHelper.COLUMN_NAME, DBHelper.COLUMN_PRICE},
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String currentName = cursor.getString(0);
            String currentPrice = cursor.getString(1);
            Items.add(new Item(currentName, currentPrice));
        }
        cursor.close();
        float total = 0f;
        for (Item i : Items) {
            try {
                total += Float.parseFloat(i.getPrice());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        Result.setText("Всего товаров: " + String.valueOf(Items.size()) + " на сумму: " + String.valueOf(total) + " рублей");
    }
}

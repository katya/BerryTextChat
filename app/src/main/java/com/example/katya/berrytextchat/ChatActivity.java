package com.example.katya.berrytextchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.katya.berrytextchat.Model.Message;
import com.example.katya.berrytextchat.Model.MsgDataStore;

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class ChatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    public void sendMsg(View view) {
        EditText editMsg = (EditText)findViewById(R.id.msg_edit);
        String text = editMsg.getText().toString();
        MsgDataStore.INSTANCE.addMessage(new Message(0, text, new Date()));
        // test message
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MsgDataStore.INSTANCE.addMessage(new Message(1, text, new Date()));
        editMsg.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            openSettings();
            return true;
        }
        if (id == R.id.exit) {
            exitChat();
        }
        return super.onOptionsItemSelected(item);
    }

    private void exitChat(){
        finish();
    }

    private void openSettings(){
        Toast.makeText(ChatActivity.this, "Open Settings Option - TBD", Toast.LENGTH_SHORT).show();
    }
}

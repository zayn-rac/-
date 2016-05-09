package cn.edu.ustc.zayn.hanhaixingyun;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //variables
    private String id, password, host = "http://bbs.ustc.edu.cn";
    private boolean anonymous = false;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText idField = (EditText)findViewById(R.id.loginId),
                passworldField = (EditText)findViewById(R.id.password);
        Button loginButton = (Button)findViewById(R.id.loginButton),
                anonymousLoginButton = (Button)findViewById(R.id.anonymousLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = idField.getText().toString();
                password = passworldField.getText().toString();
                if(id.matches("") || password.matches("")){
                    Toast.makeText(getApplicationContext(), "账号或密码为空", Toast.LENGTH_LONG)
                            .show();
                }
                else {
                    Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG)
                            .show();
                    //Start exploring the web page
                    intent = new Intent(MainActivity.this, ExplorerMainPage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        anonymousLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anonymous = true;
                try {
                    id = "guest";
                    password = "";
                    String post = "id=" + id + "&pw=" + password + "&ajax=1";
                    URL url = new URL(host);
                    //Start exploring the web page
                    intent = new Intent(MainActivity.this, ExplorerMainPage.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

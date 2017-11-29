package bornbaby.com.permissionmanager;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.karan.churi.PermissionManager.PermissionManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn_all;
    private PermissionManager permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_all = findViewById(R.id.btn_all);
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMarshmallow()) {
                    permission = new PermissionManager() {
                    };
                    permission.checkAndRequestPermissions(MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this, "below Marshmallow", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        ArrayList<String> granted = permission.getStatus().get(0).granted;
        ArrayList<String> denied = permission.getStatus().get(0).denied;

    }

    boolean isMarshmallow() {
        if (Build.VERSION_CODES.M <= Build.VERSION.SDK_INT) {
            return true;
        }
        return false;
    }
}

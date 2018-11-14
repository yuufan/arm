package com.example.sujia.app;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextView text;
    Switch power;
    EditText left;
    EditText right;
    EditText up;
    EditText down;
    Button toSit;


    static int[] distance = new int[4];


    public static void init() {
        for (int i = 0; i < 4; i++)
            distance[i] = 0;
    }

    private static final int REQUEST_BLUETOOTH_PERMISSION=10;

  /*  private void requestBluetoothPermission(){
        //判断系统版本
        if (Build.VERSION.SDK_INT >= 23) {
            //检测当前app是否拥有某个权限
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);
            //判断这个权限是否已经授权过
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                //判断是否需要 向用户解释，为什么要申请该权限
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION))
                    Toast.makeText(this,"Need bluetooth permission.",
                            Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this ,new String[]
                        {Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_BLUETOOTH_PERMISSION);
                return;
            }else{
            }
        } else {
        }
    }*/

    private static final int REQUEST_ENABLE_BT = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(Build.VERSION.SDK_INT);

        text = (TextView) findViewById(R.id.textView);
        power = (Switch) findViewById(R.id.switch2);
        left = (EditText) findViewById(R.id.editText1);
        right = (EditText) findViewById(R.id.editText2);
        up = (EditText) findViewById(R.id.editText3);
        down = (EditText) findViewById(R.id.editText4);
        toSit = (Button) findViewById(R.id.button);


        init();

     BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            System.out.println("error");// 设备不支持蓝牙
        }
     if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        TextView tvDevices = (TextView)findViewById(R.id.textView2);
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                tvDevices.append(device.getName() + ":" + device.getAddress());
            }
        }

        left.setText("0");
        right.setText("0");
        up.setText("0");
        down.setText("0");

        power.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                             @Override
                                             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                 if (isChecked)
                                                     text.setText("On");
                                                 else
                                                     text.setText("Off");
                                             }
                                         }

        );
        toSit.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         try {
                                             distance[0] = Integer.parseInt(left.getText().toString());
                                             distance[1] = Integer.parseInt(right.getText().toString());
                                             distance[2] = Integer.parseInt(up.getText().toString());
                                             distance[3] = Integer.parseInt(down.getText().toString());
                                             System.out.println("l" + distance[0] + "r" + distance[1] + "u" + distance[2] + "d" + distance[2]);
                                         } catch (Exception e) {
                                             Toast.makeText(getApplicationContext(), "距离设置错误",
                                                     Toast.LENGTH_SHORT).show();
                                         }
                                     }
                                 }
        );

    }
}


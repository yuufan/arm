package com.example.sujia.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    Switch power;
    EditText left;
    EditText right;
    EditText up;
    EditText down;
    Button toSit;
    static int[] distance=new int[4];

    public static void init(){
       for(int i=0;i<4;i++)
           distance[i]=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.textView);
        power=(Switch)findViewById(R.id.switch2);
        left=(EditText)findViewById(R.id.editText1);
        right=(EditText)findViewById(R.id.editText2);
        up=(EditText)findViewById(R.id.editText3);
        down=(EditText)findViewById(R.id.editText4);
        toSit=(Button)findViewById(R.id.button);
        init();
        power.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                             @Override
                                             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                 if(isChecked)
                                                     text.setText("On");
                                                 else
                                                     text.setText("Off");
                                             }
                                         }

        );
        toSit.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         distance[0]=Integer.parseInt(left.getText().toString());
                                         distance[1]=Integer.parseInt(right.getText().toString());
                                         distance[2]=Integer.parseInt(up.getText().toString());
                                         distance[3]=Integer.parseInt(down.getText().toString());
                                         //if (distance[0] == 0 || distance[1]==0||distance[3] == 0 || distance[4]==0)
                                         //     ;
                                         //  }
                                         System.out.println("l"+distance[0]+"r"+distance[1]+"u"+distance[2]+"d"+distance[2]);
                                     }
                                 }
        );
    }
}

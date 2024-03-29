package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView tv_bmi;
    private TextView tv_weight;
    private TextView tv_height;
    private TextView tv_bmistatus;
    private TextView tv_bmivalue;
    private EditText et_weight;
    private  EditText et_feet;
    private EditText et_inches;
    private Button button_calculate;
    double weight;
    double inches;
    double feet;
    double bmi;
    String bmi_status;
    boolean flag= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_bmistatus= findViewById(R.id.tv_bmistatus);
        tv_bmivalue=findViewById(R.id.tv_bmivalue);
        et_weight=findViewById(R.id.et_weight);
        et_feet=findViewById(R.id.et_feet);
        et_inches=findViewById(R.id.et_inches);
        button_calculate=findViewById(R.id.button_calculate);

        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                et_weight.setError(null);
                et_feet.setError(null);
                et_inches.setError(null);
                String tempWeight = et_weight.getText().toString();
                String tempInches = et_inches.getText().toString();
                String tempFeet = et_feet.getText().toString();
                if (tempWeight != null && !tempWeight.equals("") && !tempWeight.equals("0")) {
                    weight = Double.parseDouble(tempWeight);
                } else {
                    flag = true;
                    et_weight.setError("Hey, Please enter weight in lb");
                }


                if (tempInches != null && !tempInches.equals("")) {
                    inches = Double.parseDouble(tempInches);
                    if (inches >= 12) {
                        et_inches.setError("Hey, Please enter less than 12 inches");
                        flag=true;
                    }
                } else{
                    flag = true;
                     et_inches.setError(("Hey, Please enter inches "));
                  }



                if (tempFeet!=null && !tempFeet.equals("")){
                    feet = Double.parseDouble(tempFeet);
                }
                else {
                    flag=true;
                    et_feet.setError("Hey, Please enter feet");
                }

                if (feet==0 && inches==0){
                    flag=true;

                }


                if (!flag){

                    feet = feet * 12;
                    bmi = (weight * 703) / ((feet + inches) * (feet + inches));
                    /*String tempbmi= Double.toString(bmi);*/
                    DecimalFormat df1 = new DecimalFormat("#.#");
                    String tempbmi= df1.format(bmi);
                    tv_bmivalue.setText("Your BMI: " + tempbmi);
                    if (bmi <= 18.5)
                        bmi_status = "Underweight";
                    else if (bmi <= 24.9)
                        bmi_status = "Normal weight";
                    else if (bmi <= 29.9)
                        bmi_status = "Overweight";
                    else
                        bmi_status = "Obese";
                    tv_bmistatus.setText("You are " + bmi_status);
                    Toast.makeText(getApplicationContext(),"BMI Calculated",Toast.LENGTH_SHORT).show();

                }
                else{
                    tv_bmistatus.setText("");
                    tv_bmivalue.setText("");
                    Toast.makeText(getApplicationContext(),"Invalid Inputs",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

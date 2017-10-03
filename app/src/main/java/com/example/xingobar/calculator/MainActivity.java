package com.example.xingobar.calculator;


import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtCalculator = null;
    private Button btnZero = null;
    private Button btnOne = null;
    private Button btnTwo = null;
    private Button btnThree = null;
    private Button btnFour = null;
    private Button btnFive = null;
    private Button btnSix = null;
    private Button btnSeven = null;
    private Button btnEight = null;
    private Button btnNine = null;
    private Button btnPlus = null;
    private Button btnMinus = null;
    private Button btnMultiply = null;
    private Button btnDivide = null;
    private Button btnC = null;
    private Button btnDecimal = null;
    private Button btnEqual = null;
    private Button btnSqrtRoot = null;
    private Button btnPerc = null; // 取餘數
    private Button btnPI = null;
    private Button btnBS = null; // backspace
    private Button btnPow =  null;
    private Button btnSqrtThird = null;

    private double number = 0.0f; // 第一次運算得值
    private double secondNumber = 0.0f; // 第二次運算得值
    //private Operator operator = null; // 運算子
    private int operator = 0;// 運算子 1 : plus , 2 : minus , 3 : multiply , 4 : divide , 5 : mod , 6 : pow , 7 : sqrt third
    private boolean hasChanged = false; // 是否需要儲存第二個數字
    private boolean isReset = false; // 是否需要重置 （當得出結果後再按數字紐的話需重置）
    private boolean isDecimal = false; // 是否增加惹小數點

    enum Operator {
        NOTHING(0),
        PLUS(1),
        MINUS(2),
        MULTIPLY(3),
        DIVIDE(4);

        private int value = 0;

        private Operator(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private final static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        // 元件初始化
        initControls();
        initScreenLayout();
    }

    private void initControls() {
        txtCalculator = (EditText) findViewById(R.id.txtCalculator);
        btnZero = (Button) findViewById(R.id.btnZero);
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);
        btnFive = (Button) findViewById(R.id.btnFive);
        btnSix = (Button) findViewById(R.id.btnSix);
        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnEight = (Button) findViewById(R.id.btnEight);
        btnNine = (Button) findViewById(R.id.btnNine);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnC = (Button) findViewById(R.id.btnC);
        btnDecimal = (Button) findViewById(R.id.btnDecimal);
        btnEqual = (Button) findViewById(R.id.btnEquals);
        btnSqrtRoot = (Button)findViewById(R.id.btnSqrRoot);
        btnPerc = (Button)findViewById(R.id.btnPerc); // 取餘數
        btnPI = (Button)findViewById(R.id.nbtn1); // pi
        btnBS = (Button)findViewById(R.id.btnBS); // backspace
        btnPow = (Button)findViewById(R.id.expEx);
        btnSqrtThird = (Button)findViewById(R.id.sqrthird);

        btnOne.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(1);
            }
        });

        btnTwo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(2);
            }
        });

        btnThree.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(3);
            }
        });

        btnFour.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(4);
            }
        });


        btnFive.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(5);
            }
        });

        btnSix.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(6);
            }
        });

        btnSeven.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(7);
            }
        });

        btnEight.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(8);
            }
        });

        btnNine.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(9);
            }
        });

        btnZero.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate(0);
            }
        });

        btnEqual.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleValue();
            }
        });

        btnPlus.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperator(1);
            }
        });

        btnMinus.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperator(2);
            }
        });

        btnMultiply.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperator(3);
            }
        });

        btnDivide.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperator(4);
            }
        });

        // 重置
        btnC.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        btnDecimal.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                addDecimalIfNotAdded();
            }
        });

        btnSqrtRoot.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                sqrtRoot();
            }
        });

        btnPerc.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                handleOperator(5);
            }
        });

        btnPI.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                setPI();
            }
        });

        btnPow.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                handleOperator(6);
            }
        });

        btnSqrtThird.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                handleOperator(7);
                handleValue();
            }
        });

        btnBS.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(txtCalculator.getText().length() == 1)
                {
                    txtCalculator.setText("0");
                }else{
                    txtCalculator.setText(txtCalculator.getText().toString().substring(0,txtCalculator.getText().length() - 1));
                }

                if(hasChanged)
                {
                    secondNumber = Double.parseDouble(txtCalculator.getText().toString());
                }else{
                    number = Double.parseDouble(txtCalculator.getText().toString());
                }

                txtCalculator.setSelection(txtCalculator.getText().length());
            }
        });
    }

    private void calculate(int number) {

        //當得出結果後,再次按下計算紐時要把資料清除
        if(isReset)
        {
            txtCalculator.setText("");
            txtCalculator.setSelection(txtCalculator.getText().length());
            isReset = false;
        }

        if (this.operator == 0) {
            handleNumber(number);
            this.number = Double.valueOf(txtCalculator.getText().toString());
        } else {
            // 假如不是等於 nothing 的話
            // 要先清除掉在顯示數字
            if (!hasChanged) {
                txtCalculator.setText("");
                handleNumber(number);
                hasChanged = true;
            } else {
                handleNumber(number);
            }
            this.secondNumber = Double.valueOf(txtCalculator.getText().toString());
        }
    }

    // 輸入欄處理
    private void handleNumber(int number) {
        // 假如數字是0的話要特別處理免得重複增加0
        if (number == 0) {
            if (txtCalculator.getText().length() >= 1 && txtCalculator.getText().toString() != "0") {
                appendNumberToEditText(number);
            } else {
                txtCalculator.setText("0");
                txtCalculator.setSelection(txtCalculator.getText().length());
            }
        } else {
            if(txtCalculator.getText().length() == 1 && txtCalculator.getText().toString().equals("0"))
            {
                txtCalculator.setText(Integer.toString(number));
                txtCalculator.setSelection(txtCalculator.getText().length());
            }else{
                appendNumberToEditText(number);
            }
        }
    }

    // 顯示數字的部分要附加
    private void appendNumberToEditText(int number) {
        String text = txtCalculator.getText().toString();
        text += Integer.toString(number);
        txtCalculator.setText(text);
        txtCalculator.setSelection(text.length()); // place cursor at the end of text in edittext
    }

    //處理運算子
    private void handleOperator(int operator) {
        this.operator = operator;
    }

    // 計算結果
    // 計算完的話要把運算子設定為 nothing
    private void handleValue() {
        double total = 0.0f;
        DecimalFormat df = new DecimalFormat("#.#######");

        switch (this.operator) {
            case 1:
                total = number + secondNumber;
                break;
            case 2:
                total = number - secondNumber;
                break;
            case 3:
                total = number * secondNumber;
                break;
            case 4:
                if(secondNumber != 0.0f)
                {
                    total = number / secondNumber;
                }else{
                    total = 0.0f;
                }
                break;
            case 5:
                total = secondNumber == 0.0f ? 0.0f : number % secondNumber;
                break;
            case 6:
                total = Math.pow(number,secondNumber);
                break;
            case 7:
                if(hasChanged)
                {
                    if(secondNumber != 0.0f)
                    {
                        total = Math.cbrt(secondNumber);
                    }
                }else{
                    if(number != 0.0f)
                    {
                        total = Math.cbrt(number);
                    }
                }
                break;
        }

        txtCalculator.setText(df.format(total));
        txtCalculator.setSelection(txtCalculator.getText().length());
        this.operator = 0; // nothing
        this.number = 0.0f;
        this.secondNumber = 0.0f;
        this.hasChanged = false; // 不需改變
        this.isReset  = true; // 需重置
        this.isDecimal = false; // 未增加小數點
    }

    private void addDecimalIfNotAdded()
    {
        // 若還沒有增加小數點的話需增加小數點
        if(!isDecimal)
        {
            String text = txtCalculator.getText().toString() + ".";
            txtCalculator.setText(text);
            txtCalculator.setSelection(text.length());
            isDecimal = true;
        }
    }

    // 開二次方根
    private void sqrtRoot()
    {
        DecimalFormat df = new DecimalFormat("#.######");
        if(!txtCalculator.getText().equals("0"))
        {
           double root = Math.sqrt(Double.parseDouble(txtCalculator.getText().toString()));
           txtCalculator.setText(df.format(root));
           txtCalculator.setSelection(txtCalculator.getText().length());
        }
    }

    // pi 3.14
    private void setPI()
    {
        DecimalFormat df = new DecimalFormat("#.######");
        if(this.hasChanged)
        {
            secondNumber = Double.parseDouble(df.format(Math.PI));
        }else{
            number = Double.parseDouble(df.format(Math.PI));
        }
        txtCalculator.setText(df.format(Math.PI));
        txtCalculator.setSelection(txtCalculator.getText().length());
    }

    // ref : https://stackoverflow.com/questions/1016896/get-screen-dimensions-in-pixels
    private void initScreenLayout()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if(height < 400 || width < 300)
        {
            txtCalculator.setTextSize(20);
        }

        if(width < 300)
        {
            btnZero.setTextSize(18);
            btnOne.setTextSize(18);
            btnTwo.setTextSize(18);
            btnThree.setTextSize(18);
            btnFour.setTextSize(18);
            btnFive.setTextSize(18);
            btnSix.setTextSize(18);
            btnSeven.setTextSize(18);
            btnEight.setTextSize(18);
            btnNine.setTextSize(18);
            btnPlus.setTextSize(18);
            btnMinus.setTextSize(18);
            btnMultiply.setTextSize(18);
            btnDivide.setTextSize(18);
            btnSqrtRoot.setTextSize(18);
            btnSqrtThird.setTextSize(18);
            btnPI.setTextSize(18);
            btnBS.setTextSize(18);
            btnC.setTextSize(18);
            btnPow.setTextSize(18);
            btnDecimal.setTextSize(18);
            btnEqual.setTextSize(18);
            btnPerc.setTextSize(18);
        }
    }

    // 重設
    private void reset() {
        this.number = 0.0f;
        this.secondNumber = 0.0f;
        txtCalculator.setText("0");
        txtCalculator.setSelection(txtCalculator.getText().length());
        this.operator = 0;
        this.isReset = false; // 還不需重置
        this.isDecimal = false; // 還沒增加小數點
        this.hasChanged = false; //還未改變
    }
}

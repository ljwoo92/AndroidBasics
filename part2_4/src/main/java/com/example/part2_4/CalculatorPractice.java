package com.example.part2_4;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;


public class CalculatorPractice extends AppCompatActivity {

    public CalculatorPractice() {
        needRefresh_ = false;
        updateSecondOperand_ = false;
        firstOperand_ = new BigInteger("0");
        secondOperand_ = new BigInteger("0");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_practice);

        TextView mainText = findViewById(R.id.tvMainText);
        // numbers
        NumberClickListener numberClickListener = new NumberClickListener(mainText);
        findViewById(R.id.btn0).setOnClickListener(numberClickListener);
        findViewById(R.id.btn1).setOnClickListener(numberClickListener);
        findViewById(R.id.btn2).setOnClickListener(numberClickListener);
        findViewById(R.id.btn3).setOnClickListener(numberClickListener);
        findViewById(R.id.btn4).setOnClickListener(numberClickListener);
        findViewById(R.id.btn5).setOnClickListener(numberClickListener);
        findViewById(R.id.btn6).setOnClickListener(numberClickListener);
        findViewById(R.id.btn7).setOnClickListener(numberClickListener);
        findViewById(R.id.btn8).setOnClickListener(numberClickListener);
        findViewById(R.id.btn9).setOnClickListener(numberClickListener);

        // operators
        OperatorClickListener operatorClickListener = new OperatorClickListener(mainText);
        findViewById(R.id.btnDelete).setOnClickListener(operatorClickListener);
        findViewById(R.id.btnPlus).setOnClickListener(operatorClickListener);
        findViewById(R.id.btnSubtract).setOnClickListener(operatorClickListener);
        findViewById(R.id.btnMultiply).setOnClickListener(operatorClickListener);
        findViewById(R.id.btnDivide).setOnClickListener(operatorClickListener);
        findViewById(R.id.btnResult).setOnClickListener(operatorClickListener);
    }

    class OperatorClickListener implements View.OnClickListener {
        OperatorClickListener(TextView mainText) {
            mainText_ = mainText;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnDelete: {
                    String str = (String) mainText_.getText();
                    if (str.length() <= 1) {
                        if (!str.equals("0")) {
                            mainText_.setText("0");
                        }
                    } else {
                        str = str.substring(0, str.length() - 1);
                        mainText_.setText(str);
                    }
                    break;
                }
                case R.id.btnPlus: {
                    firstOperand_ = new BigInteger((String) mainText_.getText());
                    selectedOperator_ = Operator.PLUS;
                    needRefresh_ = true;
                    updateSecondOperand_ = true;
                    break;
                }
                case R.id.btnSubtract: {
                    selectedOperator_ = Operator.SUBTRACT;
                    firstOperand_ = new BigInteger((String) mainText_.getText());
                    needRefresh_ = true;
                    updateSecondOperand_ = true;
                    break;
                }
                case R.id.btnMultiply: {
                    selectedOperator_ = Operator.MULTIPLY;
                    firstOperand_ = new BigInteger((String) mainText_.getText());
                    needRefresh_ = true;
                    updateSecondOperand_ = true;
                    break;
                }
                case R.id.btnDivide: {
                    selectedOperator_ = Operator.DIVIDE;
                    firstOperand_ = new BigInteger((String) mainText_.getText());
                    needRefresh_ = true;
                    updateSecondOperand_ = true;
                    break;
                }
                case R.id.btnResult: {
                    calculate();
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }

        private void calculate() {
            try {
                String str;
                BigInteger result = new BigInteger("0");

                if (updateSecondOperand_) {
                    updateSecondOperand_ = false;
                    secondOperand_ = new BigInteger((String) mainText_.getText());
                }

                if (selectedOperator_ == Operator.PLUS) {
                    result = firstOperand_.add(secondOperand_);
                } else if (selectedOperator_ == Operator.SUBTRACT) {
                    result = firstOperand_.subtract(secondOperand_);
                } else if (selectedOperator_ == Operator.MULTIPLY) {
                    result = firstOperand_.multiply(secondOperand_);
                } else if (selectedOperator_ == Operator.DIVIDE) {
                    if (secondOperand_.longValue() == 0) {
                        throw new ArithmeticException();
                    }
                    result = firstOperand_.divide(secondOperand_);
                } else if (selectedOperator_ == Operator.NONE) {
                } else {
                }

                firstOperand_ = result;
                str = result.toString();
                mainText_.setText(str);
            } catch (ArithmeticException e) {
                firstOperand_ = new BigInteger("0");
                firstOperand_ = new BigInteger("0");
                selectedOperator_ = Operator.NONE;
                needRefresh_ = false;
                mainText_.setText(R.string.strDivideByZeroWarning);
            }
        }


        private final TextView mainText_;

    }

    class NumberClickListener implements View.OnClickListener {
        NumberClickListener(TextView mainText) {
            mainText_ = mainText;
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn0) {
                refreshMainTextNumString("0");
            } else if (view.getId() == R.id.btn1) {
                refreshMainTextNumString("1");
            } else if (view.getId() == R.id.btn2) {
                refreshMainTextNumString("2");
            } else if (view.getId() == R.id.btn3) {
                refreshMainTextNumString("3");
            } else if (view.getId() == R.id.btn4) {
                refreshMainTextNumString("4");
            } else if (view.getId() == R.id.btn5) {
                refreshMainTextNumString("5");
            } else if (view.getId() == R.id.btn6) {
                refreshMainTextNumString("6");
            } else if (view.getId() == R.id.btn7) {
                refreshMainTextNumString("7");
            } else if (view.getId() == R.id.btn8) {
                refreshMainTextNumString("8");
            } else if (view.getId() == R.id.btn9) {
                refreshMainTextNumString("9");
            }
        }

        private void refreshMainTextNumString(String numStr) {
            String str = (String) mainText_.getText();
            if (str.equals("0") || str.equals("#DIV/0!") || needRefresh_) {
                if (needRefresh_)
                    needRefresh_ = false;
                mainText_.setText(numStr);
            } else {
                str += numStr;
                mainText_.setText(str);
            }
        }

        private final TextView mainText_;
    }

    private enum Operator {NONE, PLUS, SUBTRACT, MULTIPLY, DIVIDE}

    private boolean needRefresh_;
    private boolean updateSecondOperand_;

    private BigInteger firstOperand_;
    private BigInteger secondOperand_;
    private Operator selectedOperator_;
}

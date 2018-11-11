package com.example.shafaet.a2numberchecking;

public class Number {
    int number;

    public boolean isTriangle() {

        int x = 1;
        int triangleNumber = 1;

        while(triangleNumber < number) {
            x++;
            triangleNumber += x;
        }

        if(triangleNumber == number) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isSquare() {
        double sqrtNumber = Math.sqrt(number);
        if(sqrtNumber == Math.floor(sqrtNumber)) {
            return true;
        }
        else {
            return false;
        }
    }
}

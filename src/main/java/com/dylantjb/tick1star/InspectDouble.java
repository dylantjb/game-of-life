package com.dylantjb.tick1star;

public class InspectDouble {
    public static void main(String[] args) {
        double d = Double.parseDouble(args[0]);
        // return the bits which represent the floating point number
        long bits = Double.doubleToLongBits(d);

        // Sign bit located in bit 63
        // Suggested Mask 0x8000000000000000L
        // Format 1 => number is negative
        boolean negative = (bits & 0x8000000000000000L) != 0;

        // Exponent located in bits 52 - 62
        // Suggested Mask 0x7ff0000000000000L
        // format Sum( 2^n * e(n)) - 1023 (binary number with bias)
        long exponentBits = bits & 0x7ff0000000000000L;
        int exponent = exponentToDecimal(exponentBits);


        // Mantissa located in bits 0 - 51
        // Mask left as an exercise for the reader
        // format 1 + Sum(2^-(n+1) * m(n) )
        long mantissaBits = bits & 0xfffffffffffffL;
        double mantissa = mantissaToDecimal(mantissaBits);

        System.out.println((negative ? "-" : "") + mantissa + " x 2^" + exponent);
    }

    private static int exponentToDecimal(long exponentBits) {
        long one = 0x0010000000000000L;
        return (int) ((double) exponentBits / (double) one - 1023);
    }

    private static double mantissaToDecimal(long mantissaBits) {
        long one = 0x0010000000000000L;
        return (double) (mantissaBits + one) / (double) one;
    }
}


/**
 * MIT License
 *
 * Copyright (c) 2020, 2022 Mark Schmieder
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is part of the MathToolkit Library
 *
 * You should have received a copy of the MIT License along with the
 * MathToolkit Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/mathtoolkit
 */
package com.mhschmieder.mathtoolkit;

import org.apache.commons.math3.util.FastMath;

/**
 * The class <code>Complex</code> defines a complex data type, and implements
 * operations with that data type.
 * <p>
 * NOTE: I intend to replace this class with the one from Apache Commons Math
 * v3, now that I have moved all unique methods to a utility class.
 */
public final class Complex {

    /**
     * Constant representing a zero vector along the real axis.
     */
    public static final Complex ZERO = new Complex( 0.0d, 0.0d );

    /**
     * Constant representing a unit vector along the real axis.
     */
    public static final Complex ONE  = new Complex( 1.0d, 0.0d );

    /**
     * Constant representing <i><b>i</b></i>, the square root of -1.
     */
    public static final Complex I    = new Complex( 0.0d, 1.0d );

    /**
     * The real part of <tt>Complex</tt> number.
     */
    private final double        real;

    /**
     * The imaginary part of <tt>Complex</tt> number.
     */
    private final double        imaginary;

    /**
     * Constructs a <tt>Complex</tt> from a real number. The imaginary part is
     * zero.
     * <p>
     *
     * @param re
     *            The real number
     */
    public Complex( final double re ) {
        real = re;
        imaginary = 0.0d;
    }

    /**
     * Constructs a <tt>Complex</tt> object from real and imaginary parts.
     * <p>
     *
     * @param re
     *            Real part
     * @param im
     *            Imaginary part
     */
    public Complex( final double re, final double im ) {
        real = re;
        imaginary = im;
    }

    /**
     * Returns the absolute value (magnitude) of this {@code Complex} number.
     *
     * @return a {@code double} containing the absolute value of the
     *         {@code Complex} number
     */
    public double abs() {
        final double absRe = FastMath.abs( real );
        final double absIm = FastMath.abs( imaginary );

        // This algorithm avoids overflows that might otherwise occur when
        // evaluating FastMath.sqrt(re*re + im*im), and essentially pre-factors
        // that equation for safety.
        if ( ( absRe == 0.0d ) && ( absIm == 0.0d ) ) {
            return 0.0d;
        }
        else if ( absRe >= absIm ) {
            final double d = imaginary / real;
            return absRe * FastMath.sqrt( 1.0d + ( d * d ) );
        }
        else {
            final double d = real / imaginary;
            return absIm * FastMath.sqrt( 1.0d + ( d * d ) );
        }
    }

    /**
     * Returns the sum of two <tt>Complex</tt> values. To perform z1 + z2, write
     * <tt>z1.add(z2)</tt>. Note that this method returns a <tt>Complex</tt>
     * object, so calls to these methods can be chained. For example,
     *
     * <pre>
     * z1 + z2 + z3 == <tt> z1.add( z2 ).add( z3 ) </tt>
     *
     * </pre>
     *
     * @param z
     *            A Complex number
     * @return <tt>Complex</tt> sum z1 + z2
     */
    public Complex add( final Complex z ) {
        return new Complex( real + z.real, imaginary + z.imaginary );
    }

    /**
     * Returns the sum of a <tt>Complex</tt> and a <tt>double</tt> value. To
     * perform z1 + d2, write <tt>z1.add(d2)</tt>.
     *
     * @param d
     *            A <tt>double</tt> value
     * @return <tt>Complex</tt> sum z1 + d2
     */
    public Complex add( final double d ) {
        return new Complex( real + d, imaginary );
    }

    /**
     * Returns the principal angle of this <tt>Complex</tt> number, in radians,
     * measured counter-clockwise from the real axis. <tt>arg()</tt> always
     * returns a <tt>double</tt> between -<tt><b>PI</b></tt> and +
     * <tt><b>PI</b></tt>.
     *
     * @return <tt>double</tt> containing the principal angle of the
     *         <tt>Complex</tt> value
     */
    public double getArgument() {
        return FastMath.atan2( imaginary, real );
    }

    /**
     * Returns the <tt>Complex</tt> conjugate of a <tt>Complex</tt> object. The
     * <tt>Complex</tt> conjugate of a number is the complex number having the
     * same real component and the negative of the imaginary component.
     *
     * @return <tt>Complex</tt> conjugate.
     */
    public Complex conjugate() {
        return new Complex( real, -imaginary );
    }

    /**
     * Implements division between two complex numbers.
     * <p>
     * To perform z1 / z2, write <tt>z1.div(z2)</tt>. Note that this method
     * returns a <tt>Complex</tt> object, so calls to these methods can be
     * chained. For example,
     *
     * <pre>
     * z1 * z2 / z3 == <tt> z1.mul( z2 ).div( z3 ) </tt>
     * </pre>
     *
     * @param z
     *            The {@link Complex} number to use as the divisor
     * @return The {@link Complex} number that results from the complex division
     */
    public Complex divide( final Complex z ) {
        // Calculate to minimize roundoff errors. This algorithm is inspired by
        // "Numerical Recipes in Fortran 77: The Art of Scientific Computing".
        final double x = real;
        final double y = imaginary;
        double ratio;
        double scalar;
        double zRe;
        double zIm;
        if ( FastMath.abs( x ) >= FastMath.abs( y ) ) {
            ratio = y / x;
            scalar = 1.0d / ( x + ( y * ratio ) );
            zRe = scalar * ( z.real + ( z.imaginary * ratio ) );
            zIm = scalar * ( z.imaginary - ( z.real * ratio ) );
        }
        else {
            ratio = x / y;
            scalar = 1.0d / ( ( x * ratio ) + y );
            zRe = scalar * ( ( z.real * ratio ) + z.imaginary );
            zIm = scalar * ( ( z.imaginary * ratio ) - z.real );
        }

        // Return the results in a new complex object
        return new Complex( zRe, zIm );
    }

    /**
     * Returns the division of a <tt>Complex</tt> value by a <tt>double</tt>
     * value. To perform z1 / d2, write <tt>z1.div(d2)</tt>.
     *
     * @param d
     *            A <tt>double</tt> number
     * @return <tt>Complex</tt> result of z1 / d2
     */
    public Complex divide( final double d ) {
        return new Complex( real / d, imaginary / d );
    }

    /**
     * Decides if two <tt>Complex</tt> numbers are "sufficiently" alike to be
     * considered equal. This test should be used cautiously because of the
     * inexact nature of floating-point representations on computers. This test
     * takes the form <tt> z1.equals(z2)</tt>. The tolerance for this comparison
     * is scaled automatically based on the sizes of the numbers being compared.
     *
     * @param z
     *            A <tt>Complex</tt> number
     * @return <tt>true</tt> if equal; <tt>false</tt> otherwise
     */
    public boolean equals( final Complex z ) {
        return equals( z, 1.0E-13 * abs() );
    }

    /**
     * Decides if two <tt>Complex</tt> numbers are "sufficiently" alike to be
     * considered equal. This test should be used cautiously because of the
     * inexact nature of floating-point representations on computers. This test
     * takes the form <tt> z1.equals(z2,tol)</tt>. The argument <tt>tol</tt> is
     * a scale factor used to determine the maximum magnitude of the difference
     * between them before they are considered <i>not</i> equal. This value is
     * multiplied by the absolute value of the first of the two numbers being
     * compared to make the tolerance relative to the magnitude of the numbers
     * being compared.
     *
     * @param z
     *            A <tt>Complex</tt> number
     * @param tol
     *            The tolerance with which to compare the two numbers
     * @return <tt>true</tt> if equal; <tt>false</tt> otherwise
     */
    public boolean equals( final Complex z, final double tol ) {
        final double tol1 = FastMath.abs( tol ) * abs();

        return ( FastMath.abs( real - z.real ) <= tol1 )
                && ( FastMath.abs( imaginary - z.imaginary ) <= tol1 );
    }

    /**
     * Returns the exponential number <i>e</i> (i.e., 2.718...) raised to the
     * power of this <code>Complex</code> value z.
     *
     * @return the value <i>e</i><sup>z</sup>, where <i>e</i> is the base of the
     *         natural logarithms.
     */
    public Complex exp() {
        final double scalar = FastMath.exp( real );

        return new Complex( scalar * FastMath.cos( imaginary ),
                            scalar * FastMath.sin( imaginary ) );
    }

    /**
     * Extracts the imaginary part of a <tt>Complex</tt> object as a
     * <tt>double</tt>.
     *
     * @return <tt>double</tt> containing the imaginary part of the
     *         <tt>Complex</tt> object
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Returns the reciprocal of this <tt>Complex</tt> number (1/z).
     *
     * @return The <tt>Complex</tt> reciprocal
     */
    public Complex reciprocal() {
        final double x = real;
        final double y = imaginary;
        double ratio, scalar, zRe, zIm;

        // Calculate to minimize roundoff errors. This algorithm is inspired by
        // "Numerical Recipes in Fortran 77: The Art of Scientific Computing".
        if ( FastMath.abs( x ) >= FastMath.abs( y ) ) {
            ratio = y / x;
            scalar = 1.0d / ( x + ( y * ratio ) );
            zRe = scalar;
            zIm = -scalar * ratio;
        }
        else {
            ratio = x / y;
            scalar = 1.0d / ( ( x * ratio ) + y );
            zRe = scalar * ratio;
            zIm = -scalar;
        }

        return new Complex( zRe, zIm );
    }

    /**
     * Returns <tt>true</tt> if either the real or imaginary component of this
     * <tt>Complex</tt> has an infinite value.
     *
     * @return <tt>true</tt> if either component of the <tt>Complex</tt> object
     *         is infinite; <tt>false</tt>, otherwise.
     */
    public boolean isInfinite() {
        return ( Double.isInfinite( real ) || Double.isInfinite( imaginary ) );
    }

    /**
     * Returns <tt>true</tt> if either the real or the imaginary component of
     * this <tt>Complex</tt> is a Not-a-Number (<tt>NaN</tt>) value.
     *
     * @return <tt>true</tt> if either component of the <tt>Complex</tt> object
     *         is <tt>NaN</tt>; <tt>false</tt>, otherwise.
     */
    public boolean isNaN() {
        return ( Double.isNaN( real ) || Double.isNaN( imaginary ) );
    }

    /**
     * Returns the principal natural logarithm of this {@code Complex} number.
     *
     * @return the value ln&nbsp;<code>z</code>, the natural logarithm of
     *         <code>z</code>.
     */
    public Complex log() {
        return new Complex( FastMath.log( abs() ), getArgument() );
    }

    /**
     * Returns the product of two <tt>Complex</tt> values. To perform z1 * z2,
     * write <tt>z1.mul(z2)</tt>. Note that this method returns a
     * <tt>Complex</tt> object, so calls to these methods can be chained. For
     * example,
     *
     * <pre>
     * z1 * z2 / z3 == <tt> z1.mul( z2 ).div( z3 ) </tt>
     *
     * </pre>
     *
     * @param z
     *            A Complex number
     * @return <tt>Complex</tt> product z1 * z2
     */
    public Complex multiply( final Complex z ) {
        return new Complex( ( real * z.real ) - ( imaginary * z.imaginary ),
                            ( real * z.imaginary ) + ( imaginary * z.real ) );
    }

    /**
     * Returns the product of a <tt>Complex</tt> and a <tt>double</tt> value. To
     * perform z1 * d2, write <tt>z1.mul(d2)</tt>.
     *
     * @param d
     *            A <tt>double</tt> value
     * @return <tt>Complex</tt> product z1 * d2
     */
    public Complex multiply( final double d ) {
        return new Complex( real * d, imaginary * d );
    }

    /**
     * Returns the negative of a <tt>Complex</tt> value.
     *
     * @return Negative of <tt>Complex</tt> value
     */
    public Complex negate() {
        return new Complex( -real, -imaginary );
    }

    /**
     * Returns the <tt>Complex</tt> base raised to the power of the
     * <tt>Complex</tt> exponent.
     *
     * @param exponent
     *            The <tt>Complex</tt> exponent
     * @return <tt>Complex</tt> base raised to the power of the <tt>Complex</tt>
     *         exponent.
     */
    public Complex pow( final Complex exponent ) {
        final double re = FastMath.log( abs() );
        final double imag = getArgument();

        final double re2 = ( re * exponent.real ) - ( imag * exponent.imaginary );
        final double im2 = ( re * exponent.imaginary ) + ( imag * exponent.real );

        final double scalar = FastMath.exp( re2 );

        return new Complex( scalar * FastMath.cos( im2 ), scalar * FastMath.sin( im2 ) );
    }

    /**
     * Returns the <tt>Complex</tt> value raised to the power of the
     * <tt>double</tt> exponent.
     *
     * @param exponent
     *            The <tt>double</tt> exponent
     * @return <tt>Complex</tt> base raised to the power of the exponent. * @see
     *         Complex#pow(Complex)
     */
    public Complex pow( final double exponent ) {
        final double re = exponent * FastMath.log( abs() );
        final double imag = exponent * getArgument();

        final double scalar = FastMath.exp( re );

        return new Complex( scalar * FastMath.cos( imag ), scalar * FastMath.sin( imag ) );
    }

    /**
     * Extracts the real part of a <tt>Complex</tt> object as a <tt>double</tt>.
     *
     * @return <tt>double</tt> containing the real part of the <tt>Complex</tt>
     *         object
     */
    public double getReal() {
        return real;
    }

    /**
     * Returns the difference of two <tt>Complex</tt> values. To perform z1 -
     * z2, write <tt>z1.sub(z2)</tt>. Note that this method returns a
     * <tt>Complex</tt> object, so calls to these methods can be chained. For
     * example,
     *
     * <pre>
     * z1 - z2 + z3 == <tt> z1.sub( z2 ).add( z3 ) </tt>
     *
     * </pre>
     *
     * @param z
     *            A Complex number
     * @return <tt>Complex</tt> difference z1 - z2
     */
    public Complex subtract( final Complex z ) {
        return new Complex( real - z.real, imaginary - z.imaginary );
    }

    /**
     * Returns the difference between a <tt>Complex</tt> and a <tt>double</tt>
     * value. To perform z1 - d2, write <tt>z1.sub(d2)</tt>.
     *
     * @param d
     *            A <tt>double</tt> value
     * @return <tt>Complex</tt> difference z1 - d2
     */
    public Complex subtract( final double d ) {
        return new Complex( real - d, imaginary );
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "(" + real + ", " + imaginary + ")";
    }

}

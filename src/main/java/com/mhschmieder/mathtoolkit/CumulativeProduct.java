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

import java.util.List;

/**
 * Methods related to computing the cumulative product of an array of values,
 * in place in most cases but also returned for flexibility of coding context.
 * <p>
 * Note that for most if not all methods, the start index should be the second
 * array element and not the first, as first iteration grabs previous element.
 * <p>
 * This utility class is designed to be at least a partial replacement for the
 * Matlab function with the same name ("cumprod"), but as Matlab has gone
 * strongly in a Functional programming direction the past few years, it may
 * be difficult to come up with exact equivalent in Java for each possibility.
 */
public class CumulativeProduct {

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private CumulativeProduct() {}

    /**
     * Computes the cumulative product of the specified array in place.
     *
     * Each element <code>x[i]</code> is replaced with
     * <code>x[0] * ... * x[i]</code>.
     *
     * @param x
     *            The array of doubles for which to compute the cumulative
     *            product.
     * @return A reference to <code>x</code>.
     */
    public static double[] cumprod( final double[] x ) {
        return cumprod( x, 1, x.length );
    }

    /**
     * Computes the cumulative product of the specified array in place.
     *
     * Each element <code>x[i]</code> is replaced with
     * <code>x[startIndex-1] * ... * x[stopIndex]</code>.
     *
     * @param x
     *            The array of doubles for which to compute the cumulative
     *            product.
     * @param startIndex
     *            The first index to use for applying the product against the
     *            previous array element
     * @param stopIndex
     *            The final index of the array to use for applying cumulative
     *            product
     * @return A reference to <code>x</code>.
     */
    public static double[] cumprod( final double[] x, final int startIndex, final int stopIndex ) {
        for ( int i = startIndex; i <= stopIndex; i++ ) {
            x[ i ] *= x[ i - 1 ];
        }
        return x;
    }

    /**
     * Computes the cumulative product of the specified array in place.
     *
     * Each element <code>x[i]</code> is replaced with
     * <code>x[0] * ... * x[i]</code>.
     *
     * @param x
     *            The array of ints for which to compute the cumulative product.
     * @return A reference to <code>x</code>.
     */
    public static int[] cumprod( final int[] x ) {
        return cumprod( x, 1, x.length );
    }

    /**
     * Computes the cumulative product of the specified array in place.
     *
     * Each element <code>x[i]</code> is replaced with
     * <code>x[startIndex-1] * ... * x[stopIndex]</code>.
     *
     * @param x
     *            The array of ints for which to compute the cumulative product.
     * @param startIndex
     *            The first index to use for applying the product against the
     *            previous array element
     * @param stopIndex
     *            The final index of the array to use for applying cumulative
     *            product
     * @return A reference to <code>x</code>.
     */
    public static int[] cumprod( final int[] x, final int startIndex, final int stopIndex ) {
        for ( int i = startIndex; i <= stopIndex; i++ ) {
            x[ i ] *= x[ i - 1 ];
        }
        return x;
    }

    /**
     * Computes the cumulative product of the specified array in place.
     *
     * Each element <code>x[i]</code> is replaced with
     * <code>x[0] * ... * x[i]</code>.
     *
     * @param x
     *            The array of longs for which to compute the cumulative
     *            product.
     * @return A reference to <code>x</code>.
     */
    public static long[] cumprod( final long[] x ) {
        return cumprod( x, 1, x.length );
    }

    /**
     * Computes the cumulative product of the specified array in place.
     *
     * Each element <code>x[i]</code> is replaced with
     * <code>x[startIndex-1] * ... * x[stopIndex]</code>.
     *
     * @param x
     *            The array of longs for which to compute the cumulative
     *            product.
     * @param startIndex
     *            The first index to use for applying the product against the
     *            previous array element
     * @param stopIndex
     *            The final index of the array to use for applying cumulative
     *            product
     * @return A reference to <code>x</code>.
     */
    public static long[] cumprod( final long[] x, final int startIndex, final int stopIndex ) {
        for ( int i = startIndex; i <= stopIndex; i++ ) {
            x[ i ] *= x[ i - 1 ];
        }
        return x;
    }

    /**
     * Computes the cumulative product of the specified <code>List</code> in
     * place.
     *
     * Each element <code>x.get(i)</code> is replaced with
     * <code>x.get(0) * ... * x.get(1)</code>.
     *
     * @param x
     *            The <code>List</code> of doubles for which to compute the
     *            cumulative product.
     * @return A reference to <code>x</code>.
     */
    public static List< Double > cumprod( final List< Double > x ) {
        return cumprod( x, 1, x.size() - 1 );
    }

    /**
     * Computes the cumulative product of the specified <code>List</code> in
     * place.
     *
     * Each element <code>x.get(i)</code> is replaced with
     * <code>x.get(0) * ... * x.get(1)</code>.
     *
     * @param x
     *            The <code>List</code> of doubles for which to compute the
     *            cumulative product.
     * @return A reference to <code>x</code>.
     */
    public static List< Double > cumprod( final List< Double > x,
                                          final int startIndex,
                                          final int stopIndex ) {
        for ( int i = startIndex; i <= stopIndex; i++ ) {
            x.set( i, x.get( i ) * x.get( i - 1 ) );
        }
        return x;
    }
}

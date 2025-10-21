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
 * This file is part of the JMath Library
 *
 * You should have received a copy of the MIT License along with the
 * JMath Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/jmath
 */
package com.mhschmieder.jmath.statistics;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.util.FastMath;

import java.util.Arrays;

/**
 * This class provides statistical methods which compute properties of a data
 * set and cumulative distribution methods for a few standard distributions.
 */
public final class Statistics {

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private Statistics() {}

    // This method calculates the cumulative probability curve of array x at
    // locations y.
    // TODO: See if this can be replaced -- even inside a summation loop that
    // is ours -- with something in Apache Commons Math Distribution package.
    public static double[] cumulativeProbability( final double[] x, final double[] y ) {
        // Sort into ascending order
        Arrays.sort( x );

        // Create cumulative probability array
        final double[] cumProb = new double[ y.length ];

        // Calculate cumulative counts
        for ( int i = 0; i < y.length; i++ ) {
            for ( final double element : x ) {
                if ( element <= y[ i ] ) {
                    cumProb[ i ]++;
                }
            }
        }

        // Convert to cumulative probability
        for ( int i = 0; i < y.length; i++ ) {
            cumProb[ i ] = cumProb[ i ] / x.length;
        }

        return cumProb;
    }

    // This method linearly interpolates the (x,y) data at points x0. In this
    // method, if x0 < x[0], y0 = y[0], and if x0 > x[x.length-1], y0 =
    // y[x.length-1].
    public static double interp1( final double[] x, final double[] y, final double x0 ) {
        // We know data will be monotonically increasing, so don't bother to
        // check here. Get straight to it.
        double y0;
        if ( x0 < x[ 0 ] ) {
            y0 = y[ 0 ];
        }
        else if ( x0 >= x[ x.length - 1 ] ) {
            y0 = y[ x.length - 1 ];
        }
        else {
            // Find the proper interval.
            int i = 0;
            for ( i = 0; i < ( x.length - 1 ); i++ ) {
                if ( ( x0 >= x[ i ] ) && ( x0 <= x[ i + 1 ] ) ) {
                    break;
                }
            }

            // Now interpolate the value.
            if ( i < x.length ) {
                final double dx = ( x0 - x[ i ] ) / ( x[ i + 1 ] - x[ i ] );
                y0 = y[ i ] + ( ( y[ i + 1 ] - y[ i ] ) * dx );
            }
            else {
                y0 = y[ x.length - 1 ];
            }
        }

        return y0;
    }

    // This method linearly interpolates the (x,y) data at points x0. In this
    // method, if x0 < x[0], y0 = NaN, and if x0 > x[x.length-1], y0 = NaN.
    public static double interp2( final double[] x, final double[] y, final double x0 ) {
        // We know data will be monotonically increasing, so don't bother to
        // check here. Get straight to it.
        double y0;
        if ( x0 < x[ 0 ] ) {
            y0 = Double.NaN;
        }
        else if ( x0 > x[ x.length - 1 ] ) {
            y0 = Double.NaN;
        }
        else {
            // Find the proper interval.
            int i = 0;
            for ( i = 0; i < ( x.length - 1 ); i++ ) {
                if ( ( x0 >= x[ i ] ) && ( x0 <= x[ i + 1 ] ) ) {
                    break;
                }
            }

            // Now interpolate the value.
            if ( i < x.length ) {
                final double dx = ( x0 - x[ i ] ) / ( x[ i + 1 ] - x[ i ] );
                y0 = y[ i ] + ( ( y[ i + 1 ] - y[ i ] ) * dx );
            }
            else {
                y0 = y[ x.length - 1 ];
            }
        }

        return y0;
    }

    // TODO: Find the best way to switch to Apache Commons Math Median.
    public static double median( final double[] x ) {
        double median = 0;

        if ( x.length < 1 ) {
            return 0.0d;
        }

        // Get local copy of data.
        final double[] out = new double[ x.length ];
        System.arraycopy( x, 0, out, 0, x.length );

        // Sort the data, so simplify the median algorithm.
        Arrays.sort( out );

        // Get the median. If an odd number of array elements, this is
        // trivially a query for the center element in the array.
        final int medianIndex = ( int ) FastMath.round( 0.5d * out.length );
        if ( ( out.length % 2 ) == 0 ) {
            median = 0.5d * ( out[ medianIndex - 1 ] + out[ medianIndex ] );
        }
        else {
            median = out[ medianIndex ];
        }

        return median;
    }

    // This method calculates the locations of the specified percentiles p of
    // array x.
    // NOTE: Apache Commons Math has a StatUtils class with percentile
    // methods, but they only take a single percentile input value.
    // TODO: See if we can call one of those methods inside a loop.
    public static double[] percentile( final double[] x, final double[] p ) {
        // Get percentile locations array
        final double[] percLoc = new double[ p.length ];

        // Get minimum and maximum.
        final double max = StatUtils.max( x );
        final double min = StatUtils.min( x );

        // Get independent array for cumulative probability calculation.
        final double dx = ( max - min ) / 99d;
        final double[] xval = new double[ 100 ];
        for ( int i = 0; i < xval.length; i++ ) {
            xval[ i ] = min + ( i * dx );
        }

        // Calculate cumulative probability.
        final double[] cumProb = cumulativeProbability( x, xval );

        // Calculate desired percentiles.
        for ( int i = 0; i < p.length; i++ ) {
            percLoc[ i ] = interp2( cumProb, xval, p[ i ] );
        }

        return percLoc;
    }

    public static double range( final double[] x ) {
        return StatUtils.max( x ) - StatUtils.min( x );
    }

    // Sample standard deviation (dividing by n - 1). This method calculates
    // standard deviation of a data set. This method normalizes by n-1, where n
    // is the sequence length, so it is the best unbiased estimate of standard
    // deviation if the data set is sampled from a normal distribution.
    public static double standardDeviation( final double[] x ) {
        return FastMath.sqrt( StatUtils.populationVariance( x ) );
    }
}

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
package com.mhschmieder.jmath;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.FastMath;

/**
 * Container for mathematical constants.
 */
public final class MathConstants {

    /**
     * The default constructor is disabled, as this is a static constants class.
     */
    private MathConstants() {}

    /**
     * Constant representing <i><b>i</b></i>, the square root of -1. (For
     * electrical engineers, who use the "J" terminology instead of "I").
     */
    public static final Complex J                    = Complex.I;

    // For use in calculating log base 2. An ln(x) times this is a log base 2.
    public static final double  LN2                  = FastMath.log( 2.0d );
    public static final double  LN2_SCALE            = 1.0d / LN2;

    // For use in calculating log base 10. An ln(x) times this is a log base 10.
    public static final double  LN10                 = FastMath.log( 10.0d );
    public static final double  LN10_SCALE           = 1.0d / LN10;

    // Smallest relative spacing for doubles.
    public static final double  EPSILON_SMALL        = 1.11022302462515e-16;

    // Largest relative spacing for doubles.
    public static final double  EPSILON_LARGE        = 2.2204460492503e-16;

    // Common ratios, cached for efficiency.
    // NOTE: Redundant ratios are provided for equation clarity.
    public static final double  ONE_HALF             = 0.5d;
    public static final double  ONE_THIRD            = 1.0d / 3.0d;
    public static final double  ONE_FOURTH           = 0.25d;
    public static final double  ONE_FIFTH            = 0.2d;
    public static final double  ONE_SIXTH            = 1.0d / 6.0d;
    public static final double  ONE_SEVENTH          = 1.0d / 7.0d;
    public static final double  ONE_EIGHTH           = 0.125d;
    public static final double  ONE_NINTH            = 1.0d / 9.0d;
    public static final double  ONE_ELEVENTH         = 1.0d / 11d;
    public static final double  ONE_TWELFTH          = 1.0d / 12d;
    public static final double  TWO_HALVES           = 1.0d;
    public static final double  TWO_THIRDS           = 2.0d / 3.0d;
    public static final double  TWO_FOURTHS          = ONE_HALF;
    public static final double  TWO_FIFTHS           = 0.4d;
    public static final double  TWO_SIXTHS           = ONE_THIRD;
    public static final double  TWO_SEVENTHS         = 2.0d / 7.0d;
    public static final double  TWO_EIGHTHS          = ONE_FOURTH;
    public static final double  TWO_NINTHS           = 2.0d / 9.0d;
    public static final double  THREE_HALVES         = 1.5d;
    public static final double  THREE_THIRDS         = 1.0d;
    public static final double  THREE_FOURTHS        = 0.75d;
    public static final double  THREE_FIFTHS         = 0.6d;
    public static final double  THREE_SIXTHS         = ONE_HALF;
    public static final double  THREE_SEVENTHS       = 3.0d / 7.0d;
    public static final double  THREE_EIGHTHS        = 3.0d * ONE_EIGHTH;
    public static final double  THREE_NINTHS         = ONE_THIRD;
    public static final double  FOUR_EIGHTHS         = ONE_HALF;
    public static final double  FIVE_EIGHTHS         = 5.0d * ONE_EIGHTH;
    public static final double  SIX_EIGHTHS          = THREE_FOURTHS;
    public static final double  SEVEN_EIGHTHS        = 7.0d * ONE_EIGHTH;

    // Common square roots, cached for efficiency.
    public static final double  SQRT_TWO             = FastMath.sqrt( 2.0d );
    public static final double  SQRT_THREE           = FastMath.sqrt( 3.0d );

    // Euler's constant.
    public static final double  EULERS_CONSTANT      = 0.57721566490153286d;

    // Trigonometric constants.
    public static final double  HALF_PI              = 0.5d * FastMath.PI;
    public static final double  TWO_PI               = 2.0d * FastMath.PI;
    public static final double  THREE_PI             = 3.0d * FastMath.PI;
    public static final double  FOUR_PI              = 4.0d * FastMath.PI;
    public static final double  FIVE_PI              = 5.0d * FastMath.PI;
    public static final double  SIX_PI               = 6.0d * FastMath.PI;
    public static final double  SEVEN_PI             = 7.0d * FastMath.PI;
    public static final double  EIGHT_PI             = 8.0d * FastMath.PI;
    public static final double  SQRT_TWO_PI          = FastMath.sqrt( TWO_PI );

    // Gamma coefficients.
    public static final double  GAMMA_COEFFICIENTS[] = {
                                                         1.000000000190015d,
                                                         76.18009172947146d,
                                                         -86.50532032941677d,
                                                         24.01409824083091d,
                                                         -1.231739572450155d,
                                                         0.1208650973866179d,
                                                         -0.5395239384953e-5 };

}

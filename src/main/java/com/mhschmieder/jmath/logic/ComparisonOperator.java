/*
 * MIT License
 *
 * Copyright (c) 2024, 2025, Mark Schmieder. All rights reserved.
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
 * This file is part of the jmath Library
 *
 * You should have received a copy of the MIT License along with the jmath
 * Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/jmath
 */
package com.mhschmieder.jmath.logic;

import com.mhschmieder.jcommons.lang.EnumUtilities;
import com.mhschmieder.jcommons.lang.Indexed;
import com.mhschmieder.jcommons.lang.Labeled;

/**
 * Enumeration of comparison operators, to avoid using integers and losing track
 * of which value means which operator.
 * <p>
 * Index values are usually order-agnostic, but they are non-arbitrary in this
 * context as this enum was derived from  legacy integer-based code that
 * predates the addition of Java Generics.
 */
public enum ComparisonOperator implements Indexed< ComparisonOperator >,
        Labeled< ComparisonOperator > {
    NONE( 0, "Ignore" ),
    EQUAL( 3, "=" ),
    NOT_EQUAL( 4, "≠" ),
    GREATER_THAN( 1, ">" ),
    LESS_THAN( 2, "<" ),
    GREATER_THAN_OR_EQUAL( 5, ">=" ),
    LESS_THAN_OR_EQUAL( 6, "<=" );

    private final int index;
    private final String label;

    ComparisonOperator( final int pIndex,
                        final String pLabel ) {
        index = pIndex;
        label = pLabel;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public ComparisonOperator valueOfIndex( final int pIndex ) {
        return ( ComparisonOperator ) EnumUtilities.getIndexedEnumFromIndex(
                pIndex, values() );
    }

    @Override
    public String label() {
        return label;
    }

    @Override
    public ComparisonOperator valueOfLabel( final String text ) {
        return ( ComparisonOperator ) EnumUtilities.getLabeledEnumFromLabel(
                text, values() );
    }

    public static ComparisonOperator defaultValue() {
        return NONE;
    }

    @Override
    public String toString() {
        // NOTE: This override takes care of displaying the current choice in
        //  its custom label form when a Combo Box is hosted by a Table Cell.
        return label();
    }
}

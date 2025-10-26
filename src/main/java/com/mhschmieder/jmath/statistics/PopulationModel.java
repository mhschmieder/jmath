/*
 * MIT License
 *
 * Copyright (c) 2022, 2025, Mark Schmieder. All rights reserved.
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
package com.mhschmieder.jmath.statistics;

import com.mhschmieder.jcommons.lang.EnumUtilities;
import com.mhschmieder.jcommons.lang.Indexed;
import com.mhschmieder.jcommons.lang.Labeled;

/**
 * Enumerate the models used for counting and/or distributing a population over
 * a given area. One model's implementation may convert internally to the other.
 */
public enum PopulationModel implements Indexed< PopulationModel >,
        Labeled< PopulationModel > {
    DENSITY( 0, "Populate by Density" ),
    QUANTITY( 1, "Populate by Quantity" );

    private int index;
    private String label;

    PopulationModel( final int pIndex,
                     final String pLabel ) {
        index = pIndex;
        label = pLabel;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public PopulationModel valueOfIndex( final int pIndex ) {
        return ( PopulationModel ) EnumUtilities.getIndexedEnumFromIndex(
                pIndex, values() );
    }

    @Override
    public String label() {
        return label;
    }

    @Override
    public PopulationModel valueOfLabel( final String text ) {
        return ( PopulationModel ) EnumUtilities.getLabeledEnumFromLabel(
                text, values() );
    }

    public static PopulationModel defaultValue() {
        return DENSITY;
    }
}

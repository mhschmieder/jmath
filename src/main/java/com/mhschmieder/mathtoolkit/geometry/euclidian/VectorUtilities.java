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
package com.mhschmieder.mathtoolkit.geometry.euclidian;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.mhschmieder.mathtoolkit.MathUtilities;

/**
 * Utility methods for math; primarily consisting of basic entity methods on
 * points and lines.
 */
public class VectorUtilities {

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private VectorUtilities() {}

    public static Vector2D copyPoint2D( final Vector2D point2D ) {
        final Vector2D copiedPoint2D = new Vector2D( point2D.getX(), point2D.getY() );
        return copiedPoint2D;
    }

    public static Vector3D copyPoint3D( final Vector3D point3D ) {
        final Vector3D copiedPoint3D =
                                     new Vector3D( point3D.getX(), point3D.getY(), point3D.getZ() );
        return copiedPoint3D;
    }

    public static Vector3D exchangeCoordinates( final Vector3D point3D,
                                                final OrthogonalAxes orthogonalAxes ) {
        switch ( orthogonalAxes ) {
        case XY:
            return new Vector3D( point3D.getY(), point3D.getX(), point3D.getZ() );
        case XZ:
            return new Vector3D( point3D.getZ(), point3D.getY(), point3D.getX() );
        case YZ:
            return new Vector3D( point3D.getX(), point3D.getZ(), point3D.getY() );
        default:
            return Vector3D.ZERO;
        }
    }

    /**
     * Returns the distance between two point coordinate pairs.
     *
     * @param x1
     *            the X coordinate of the first specified point
     * @param y1
     *            the Y coordinate of the first specified point
     * @param x2
     *            the X coordinate of the second specified point
     * @param y2
     *            the Y coordinate of the second specified point
     * @return the distance between the two specified coordinate pairs.
     * @since 1.2
     */
    public static double distance( final double x1,
                                   final double y1,
                                   final double x2,
                                   final double y2 ) {
        final double dx = x1 - x2;
        final double dy = y1 - y2;
        return Math.hypot( dx, dy );
    }

    /**
     * Returns the distance from one <code>Point2D</code> to another specified
     * point coordinate pair.
     *
     * @param pt
     *            The point from which to measure distance
     * @param px
     *            the X coordinate of the specified point to be measured against
     *            this <code>Point2D</code>
     * @param py
     *            the Y coordinate of the specified point to be measured against
     *            this <code>Point2D</code>
     * @return the distance between one <code>Point2D</code> and another
     *         specified point coordinate pair
     * @since 1.2
     */
    public static double distance( final Vector2D pt, final double px, final double py ) {
        final double dx = px - pt.getX();
        final double dy = py - pt.getY();
        return Math.hypot( dx, dy );
    }

    /**
     * Returns the distance from one <code>Point2D</code> to another specified
     * <code>Point2D</code>.
     *
     * @param pt1
     *            The reference point to use for measuring another point
     * @param pt2
     *            The specified point to be measured against the reference point
     * @return the square of the distance between one <code>Point2D</code> and
     *         another specified <code>Point2D</code>.
     * @since 1.2
     */
    public static double distance( final Vector2D pt1, final Vector2D pt2 ) {
        return pt1.distance( pt2 );
    }

    /**
     * Returns the square of the distance between two points coordinate pairs.
     *
     * @param x1
     *            the X coordinate of the first specified point
     * @param y1
     *            the Y coordinate of the first specified point
     * @param x2
     *            the X coordinate of the second specified point
     * @param y2
     *            the Y coordinate of the second specified point
     * @return the square of the distance between the two specified coordinate
     *         pairs.
     * @since 1.2
     */
    public static double distanceSq( final double x1,
                                     final double y1,
                                     final double x2,
                                     final double y2 ) {
        final double dx = x1 - x2;
        final double dy = y1 - y2;
        return ( MathUtilities.sqr( dx ) + MathUtilities.sqr( dy ) );
    }

    /**
     * Returns the square of the distance from one <code>Point2D</code> to
     * another specified point coordinate pair.
     *
     * @param point
     *            The point from which to measure distance
     * @param px
     *            the X coordinate of the specified point to be measured against
     *            this <code>Point2D</code>
     * @param py
     *            the Y coordinate of the specified point to be measured against
     *            this <code>Point2D</code>
     * @return the square of the distance between one <code>Point2D</code> and
     *         another specified point coordinate pair.
     * @since 1.2
     */
    public static double distanceSq( final Vector2D point, final double px, final double py ) {
        final double dx = px - point.getX();
        final double dy = py - point.getY();
        return ( MathUtilities.sqr( dx ) + MathUtilities.sqr( dy ) );
    }

    /**
     * Returns the square of the distance from one <code>Point2D</code> to
     * another specified <code>Point2D</code>.
     *
     * @param pt1
     *            The reference point to use for measuring another point
     * @param pt2
     *            The specified point to be measured against the reference point
     * @return the square of the distance between one <code>Point2D</code> and
     *         another specified <code>Point2D</code>.
     * @since 1.2
     */
    public static double distanceSq( final Vector2D pt1, final Vector2D pt2 ) {
        return pt1.distanceSq( pt2 );
    }
}

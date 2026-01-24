/*
 * MIT License
 *
 * Copyright (c) 2020, 2026 Mark Schmieder. All rights reserved.
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
package com.mhschmieder.jmath.geometry.euclidian;

import com.mhschmieder.jmath.MathUtilities;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

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

    public static Vector2D negatePoint2D( final Vector2D point2D ) {
        final Vector2D negatedPoint2D = new Vector2D( -point2D.getX(), -point2D.getY() );
        return negatedPoint2D;
    }

    public static Vector2D negatePoint2D( final Vector2D point2D, final Axis axis ) {
        Vector2D negatedPoint = Vector2D.ZERO;
        
        switch ( axis ) {
        case X:
            negatedPoint = new Vector2D( -point2D.getX(), point2D.getY() );
            break;
        case Y:
            negatedPoint = new Vector2D( point2D.getX(), -point2D.getY() );
            break;
        case Z:
            negatedPoint = new Vector2D( point2D.getX(), point2D.getY() );
            break;
        default:
            break;
        }
        
        return negatedPoint;
    }

    public static Vector3D negatePoint3D( final Vector3D point3D ) {
        final Vector3D negatedPoint3D = new Vector3D( -point3D.getX(),
                                                      -point3D.getY(),
                                                      -point3D.getZ() );
        return negatedPoint3D;
    }

    public static Vector3D negatePoint3D( final Vector3D point3D, final Axis axis ) {
        Vector3D negatedPoint = Vector3D.ZERO;
        
        switch ( axis ) {
        case X:
            negatedPoint = new Vector3D( -point3D.getX(), point3D.getY(), point3D.getZ() );
            break;
        case Y:
            negatedPoint = new Vector3D( point3D.getX(), -point3D.getY(), point3D.getZ() );
            break;
        case Z:
            negatedPoint = new Vector3D( point3D.getX(), point3D.getY(), -point3D.getZ() );
            break;
        default:
            break;
        }
        
        return negatedPoint;
    }

    public static Vector3D exchangeCoordinates( final Vector3D point3D,
                                                final OrthogonalAxes orthogonalAxes ) {
        Vector3D swappedPoint = Vector3D.ZERO;
        
        switch ( orthogonalAxes ) {
        case XY:
            swappedPoint = new Vector3D( point3D.getY(), point3D.getX(), point3D.getZ() );
            break;
        case XZ:
            swappedPoint = new Vector3D( point3D.getZ(), point3D.getY(), point3D.getX() );
            break;
        case YZ:
            swappedPoint = new Vector3D( point3D.getX(), point3D.getZ(), point3D.getY() );
            break;
        default:
            break; 
        }
        
        return swappedPoint;
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
        return FastMath.hypot( dx, dy );
    }

    /**
     * Returns the distance from one <code>Vector2D</code> to another specified
     * point coordinate pair.
     *
     * @param pt
     *            The point from which to measure distance
     * @param px
     *            the X coordinate of the specified point to be measured against
     *            this <code>Vector2D</code>
     * @param py
     *            the Y coordinate of the specified point to be measured against
     *            this <code>Vector2D</code>
     * @return the distance between one <code>Vector2D</code> and another
     *         specified point coordinate pair
     * @since 1.2
     */
    public static double distance( final Vector2D pt, final double px, final double py ) {
        final double dx = px - pt.getX();
        final double dy = py - pt.getY();
        return FastMath.hypot( dx, dy );
    }

    /**
     * Returns the distance from one <code>Vector2D</code> to another specified
     * <code>Vector2D</code>.
     *
     * @param pt1
     *            The reference point to use for measuring another point
     * @param pt2
     *            The specified point to be measured against the reference point
     * @return the square of the distance between one <code>Vector2D</code> and
     *         another specified <code>Vector2D</code>.
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
     * Returns the square of the distance from one <code>Vector2D</code> to
     * another specified point coordinate pair.
     *
     * @param point
     *            The point from which to measure distance
     * @param px
     *            the X coordinate of the specified point to be measured against
     *            this <code>Vector2D</code>
     * @param py
     *            the Y coordinate of the specified point to be measured against
     *            this <code>Vector2D</code>
     * @return the square of the distance between one <code>Vector2D</code> and
     *         another specified point coordinate pair.
     * @since 1.2
     */
    public static double distanceSq( final Vector2D point, final double px, final double py ) {
        final double dx = px - point.getX();
        final double dy = py - point.getY();
        return ( MathUtilities.sqr( dx ) + MathUtilities.sqr( dy ) );
    }

    /**
     * Returns the square of the distance from one <code>Vector2D</code> to
     * another specified <code>Vector2D</code>.
     *
     * @param pt1
     *            The reference point to use for measuring another point
     * @param pt2
     *            The specified point to be measured against the reference point
     * @return the square of the distance between one <code>Vector2D</code> and
     *         another specified <code>Vector2D</code>.
     * @since 1.2
     */
    public static double distanceSq( final Vector2D pt1, final Vector2D pt2 ) {
        return pt1.distanceSq( pt2 );
    }

    /**
     * Returns a point which lies in the middle between one coordinate pair
     * and another coordinate pair.
     *
     * @param x1 the X coordinate of the start endpoint
     * @param y1 the Y coordinate of the start endpoint
     * @param x2 the X coordinate of the end endpoint
     * @param y2 the Y coordinate of the end endpoint
     * @return the point in the middle of the two specified points
     */
    public static Vector2D midpoint( final double x1, 
                                     final double y1,
                                     final double x2,
                                     final double y2 ) {
        return new Vector2D(
                0.5d * ( x2 - x1 ),
                0.5d * ( y2 - y1 ) );
    }

    /**
     * Returns a point which lies in the middle between one <code>Vector2D</code>
     * and another specified <code>Vector2D</code>.
     *
     * @param pt1
     *            The start point to use for finding the midpoint
     * @param pt2
     *            The end point to use for finding the midpoint
     * @return the point in the middle of the two specified points
     * @throws NullPointerException if either specified {@code point} is null
     */
    public static Vector2D midpoint( final Vector2D pt1,
                                     final Vector2D pt2 ) {
        return midpoint( pt1.getX(), pt1.getY(), pt2.getX(), pt2.getY() );
    }

    /**
     * Returns the quadrant of a 2D point relative to an origin:
     * 1, 2, 3, or 4
     *
     * @param point
     *            The point to judge relative to the origin
     * @param origin
     *            The origin to reference for determining the quadrant of the
     *            supplied 2D point
     * @return The quadrant number for a supplied 2D point
     */
    public static int getQuadrant( final Vector2D point, final Vector2D origin ) {
        if ( point.getX() < origin.getX() ) {
            if ( point.getY() >= origin.getY() ) {
                return 2;
            }
            return 3;
        }
        if ( point.getY() >= origin.getY() ) {
            return 1;
        }
        return 4;
    }

    /**
     * Returns the octant of a 3D point relative to an origin:
     * 1, 2, 3, 4, 5, 6, 7, or 8
     *
     * @param point
     *            The point to judge relative to the origin
     * @param origin
     *            The origin to reference for determining the octant of the
     *            supplied 3D point
     * @return The octant number for a supplied 3D point
     */
    public static int getOctant( final Vector3D point, final Vector3D origin ) {
        if ( point.getZ() < origin.getZ() ) {
            if ( point.getX() < origin.getX() ) {
                if ( point.getY() >= origin.getY() ) {
                    return 6;
                }
                return 7;
            }
            if ( point.getY() >= origin.getY() ) {
                return 5;
            }
            return 8;
        }
        if ( point.getX() < origin.getX() ) {
            if ( point.getY() >= origin.getY() ) {
                return 2;
            }
            return 3;
        }
        if ( point.getY() >= origin.getY() ) {
            return 1;
        }
        return 4;
    }

    public static Vector2D projectToPlane( final Vector3D point3D,
                                           final OrthogonalAxes orthogonalAxes ) {
        // Project a 3D point to a plane defined by an orthogonal axis pair.
        Vector2D projectedPoint = Vector2D.ZERO;
        
        switch ( orthogonalAxes ) {
        case XY:
            projectedPoint = new Vector2D( point3D.getX(), point3D.getY() );
            break;
        case XZ:
            projectedPoint = new Vector2D( point3D.getX(), point3D.getZ() );
            break;
        case YZ:
            projectedPoint = new Vector2D( point3D.getY(), point3D.getZ() );
            break;
        default:
            break;
        }
        
        return projectedPoint;
    }

    public static Vector3D rotateInPlane( final Vector3D point3D,
                                          final OrthogonalAxes orthogonalAxes,
                                          final double angleInRadians ) {
        double axis1Value = 0.0d;
        double axis2Value = 0.0d;
    
        switch ( orthogonalAxes ) {
        case XY:
            axis1Value = point3D.getX();
            axis2Value = point3D.getY();
            break;
        case XZ:
            axis1Value = point3D.getX();
            axis2Value = point3D.getZ();
            break;
        case YZ:
            axis1Value = point3D.getY();
            axis2Value = point3D.getZ();
            break;
        default:
            break;
        }
    
        final double axis1ValueRotated = ( axis1Value * FastMath.cos( angleInRadians ) )
                - ( axis2Value * FastMath.sin( angleInRadians ) );
    
        final double axis2ValueRotated = ( axis1Value * FastMath.sin( angleInRadians ) )
                + ( axis2Value * FastMath.cos( angleInRadians ) );
    
        Vector3D rotatedPoint = Vector3D.ZERO;
        
        switch ( orthogonalAxes ) {
        case XY:
            rotatedPoint = new Vector3D( axis1ValueRotated, axis2ValueRotated, 0.0d );
            break;
        case XZ:
            rotatedPoint = new Vector3D( axis1ValueRotated, 0.0d, axis2ValueRotated );
            break;
        case YZ:
            rotatedPoint = new Vector3D( 0.0d, axis1ValueRotated, axis2ValueRotated );
            break;
        default:
            break;
        }
        
        return rotatedPoint;
    }
}

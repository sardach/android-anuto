package ch.bfh.anuto.util;

import org.simpleframework.xml.Attribute;

public class Vector2 {

    /*
    ------ Members ------
     */

    @Attribute
    public float x;

    @Attribute
    public float y;

    /*
    ------ Constructors ------
     */

    public Vector2() {
    }

    public Vector2(float x, float y) {
        set(x, y);
    }

    public Vector2(Vector2 v) {
        set(v);
    }

    /*
    ------ Methods ------
     */

    public Vector2 copy() {
        return new Vector2(this);
    }

    public Vector2 set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2 set(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
        return this;
    }

    public Vector2 add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public Vector2 sub(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public Vector2 mul(float s) {
        this.x *= s;
        this.y *= s;
        return this;
    }

    public float dot(Vector2 v) {
        return x * v.x + y * v.y;
    }

    public float len() {
        return (float)Math.sqrt(x * x + y * y);
    }

    public float len2() {
        return x * x + y * y;
    }

    public Vector2 norm() {
        float len = len();

        if (len != 0) {
            x /= len;
            y /= len;
        }

        return this;
    }

    public float angle() {
        return (float)Math.atan2(y, x) / (float)Math.PI * 180f;
    }
}

package day9;

public class Vector2D {
    int x;
    int y;

    Vector2D leadingNode;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D createClone() {
        return new Vector2D(this.x, this.y);
    }

    @Override
    public boolean equals(Object v) {
        if (v instanceof Vector2D) {
            Vector2D vector2d = (Vector2D) v;
            if (vector2d.x == this.x && vector2d.y == this.y)
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.x + this.y;
    }

    public void attachTo(Vector2D vector2d) {
        this.leadingNode = vector2d;
    }

    public Vector2D simulateRopeMovement(){
        if(!this.isAdjacentTo(this.leadingNode))
        {
            this.follow(leadingNode);
        }
        return this;
    }

    public Vector2D add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;

        return this;
    }

    public boolean isAdjacentTo(Vector2D vector2d) {
        return vector2d.x >= this.x - 1 && vector2d.x <= this.x + 1 && vector2d.y <= this.y + 1 && vector2d.y >= this.y - 1;
    }

    public Vector2D follow(Vector2D vector2d) {
        if (vector2d.equals(this))
            return this;

        boolean isToTheRight = vector2d.x > this.x;
        boolean isUpward = vector2d.y > this.y;

        if (vector2d.y == this.y) {
            if (isToTheRight)
                this.x++;
            else
                this.x--;
        } else if (vector2d.x == this.x) {
            if (isUpward)
                this.y++;
            else
                this.y--;
        } else {
            if(isToTheRight && isUpward) {
                this.x++;
                this.y++;
            } else if(!isToTheRight && isUpward) {
                this.x--;
                this.y++;
            } else if(isToTheRight && !isUpward) {
                this.x++;
                this.y--;
            } else {
                this.x--;
                this.y--;
            }
        }

        return this;
    }
}

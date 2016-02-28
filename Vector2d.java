public class Vector2d {
  public double x;
  public double y;

  public Vector2d() {
  }

  public Vector2d(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Vector2d get() {
    return new Vector2d(x, y);
  }

  public Vector2d set(double x, double y) {
    this.x = x;
    this.y = y;
    return this;
  }

  public Vector2d add(Vector2d v) {
    x += v.x;
    y += v.y;
    return this;
  }

  public Vector2d add(double x, double y) {
    this.x += x;
    this.y += y;
    return this;
  }

  public Vector2d mult(double n) {
    this.x *= n;
    this.y *= n;
    return this;
  }

  static public double dist(Vector2d v1, Vector2d v2) {
    double dx = v1.x - v2.x;
    double dy = v1.y - v2.y;
    return (double) Math.sqrt(dx * dx + dy * dy);
  }

  static public double dist(double x1, double y1, double x2, double y2) {
    double dx = x1 - x2;
    double dy = y1 - y2;
    return (double) Math.sqrt(dx * dx + dy * dy);
  }

  static public final double map(double value,
                                 double start1, double stop1,
                                 double start2, double stop2) {
    return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
  }
}

public class Body {
    public double xxPos; //Its current x position
    public double yyPos;
    public double xxVel; //Its current velocity in the x direction
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt(Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2));
    }

    /**public and static cannot be used inside a method definition.*/
    public static final double G = 6.67e-11;
    
    public double calcForceExertedBy(Body b) {
        return (G * this.mass * b.mass) / Math.pow(this.calcDistance(b), 2);
    }

    public double calcForceExertedByX(Body b) {
        return this.calcForceExertedBy(b) * ((b.xxPos - this.xxPos) / this.calcDistance(b));

    }

    public double calcForceExertedByY(Body b) {
        return this.calcForceExertedBy(b) * ((b.yyPos - this.yyPos) / this.calcDistance(b));

    }
    
    public double calcNetForceExertedByX(Body[] allbodys) {
        double netForceX = 0;
        int len = allbodys.length;
        for (int i = 0; i < len; i++) {
            if (this.equals(allbodys[i])) {
                continue;
            }
            netForceX += this.calcForceExertedByX(allbodys[i]);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] allbodys) {
        double netForceY = 0;
        for (Body element : allbodys) {
            if (this.equals(element)) {
                continue;
            }
            netForceY = netForceY + this.calcForceExertedByY(element);
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        /**Drawing one body */
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}

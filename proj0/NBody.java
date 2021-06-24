/**to simulate a universe specified in one of the data files. */
public class NBody {
    public static double readRadius(String s) {
        /* Every time you call a read method from the In class,
         * it reads the next thing from the file, assuming it is
         * of the specified type. */
        In in = new In(s);
        int planetsNum = in.readInt(); //garbage 
        double radius = in.readDouble();
        return radius;
    }
    
    public static Body[] readBodies(String fileName) {
        /**return an array of planets in the file.*/
        In in = new In(fileName);
        int i = 0;
        int planetsNum = in.readInt();
        double radius = in.readDouble(); //garbage
        Body[] bodies = new Body[planetsNum];
        while (i < planetsNum) {
            bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readString());
            i++;
        }
        return bodies;
    }
    
    public static void main(String[] args) {
        Double T = Double.parseDouble(args[0]);
        Double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] planets = readBodies(filename);
        double radius = readRadius(filename);




        
        /**Creat an Animation */
        double updateTime = 0;
        while (updateTime <= T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            /**Drawing the Background */
            String backgroundImg = "images/starfield.jpg";
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius - 5, radius + 5);
            StdDraw.clear();
            StdDraw.picture(0, 0, backgroundImg);
            /**Drawing all of the bodies */
            for (Body planet : planets) {
                planet.draw();
            }
            StdDraw.show();

            StdDraw.pause(10);

            updateTime += dt;
        }
        
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}
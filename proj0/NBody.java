
public class NBody {

    static public double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    static public Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int number = in.readInt();
        in.readDouble();
        Planet[] res = new Planet[number];
        for (int i = 0; i < number; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            res[i] = planet;
        }
        return res;
    }

    public static String imageToDraw = "./images/starfield.jpg";

    public static void main(String[] args) {
        if (args.length < 3) {
            return;
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        Planet[] planets = readPlanets(fileName);
        StdDraw.setScale(-3e11, 3e11);
        StdDraw.enableDoubleBuffering();

        double time = 0;
        while (time < T) {
            time += dt;
            int number = planets.length;
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < number; ++i) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, imageToDraw);
            for (Planet pl : planets) {
                pl.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", readRadius(fileName));
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}

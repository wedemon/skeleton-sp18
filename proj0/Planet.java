
public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
            double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = ("images/") + img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double sum = 0.0;
        sum += Math.pow(xxPos - p.xxPos, 2);
        sum += Math.pow(yyPos - p.yyPos, 2);
        return Math.sqrt(sum);
    }

    public double calcForceExertedBy(Planet p) {
        return 6.67e-11 * mass * p.mass / Math.pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double sum = 0.0;
        for (Planet allPlanet : allPlanets) {
            if (allPlanet == this) {
                continue;
            }
            sum += calcForceExertedByX(allPlanet);
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double sum = 0.0;
        for (Planet allPlanet : allPlanets) {
            if (allPlanet == this) {
                continue;
            }
            sum += calcForceExertedByY(allPlanet);
        }
        return sum;
    }

    public void update(double dt, double fX, double fY){
        xxVel += dt * fX / mass;
        yyVel += dt * fY / mass;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public double ReadRadius(String[] fileName){
        if(fileName.length ==0){
            return 0.0;
        }
        In in = new In(fileName[0]);
        in.readInt();
        return in.readDouble();
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, imgFileName);
    }
}

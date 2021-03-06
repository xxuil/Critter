/* CRITTERS Liuxx2.java
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * Xiangxing Liu
 * xl5587
 * 76175
 * Zi Zhou Wang
 * zw3948
 * 76175
 * Slip days used: <0>
 * Git URL: https://github.com/xxuil/Critter
 * Summer 2017
 */
package assignment5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Liuxx extends Critter{

    private static  final boolean Debug = false;
    private Integer direction;
    private Integer fights;
    private boolean move;


    public Liuxx(){
        direction = Critter.getRandomInt(8);
        fights = 0;
        move = false;
    }

    public String toString(){
        return "X";
    }

    public int getDir(){
        return direction;
    }


    public void doTimeStep(){
        if(Debug){
        }
        //determine current energy
        int current = getEnergy();
        int res = (current - 50) / 20;

        if(current < 20){
            run(direction);
            move = true;
        }
        else if(current < 50){
            String foresee = look(direction, false);
            if(foresee == null){
                walk(direction);
                move = true;
            }
            else{
                //do nothing
            }
        }

        else if(current < 100){
            //do nothing
        }
        //choose to reproduce or not
        else if(current >= 100){
            Liuxx child = new Liuxx();
            int g = Critter.getRandomInt(8);
            reproduce(child, g);
        }

        //reset direction
        direction = Critter.getRandomInt(8);
    }


    public boolean fight(String opponent){
        if(opponent.equals("Algae")){
            fights ++;
            return true;
        }

        if(!move){
            if(getEnergy() > 70){
                fights ++;
                return true;
            }
            // if not, run away
            else{
                run(direction);
                return false;
            }
        }

        fights++;
        return true;
    }



    public static void runStats(java.util.List<Critter> Liuxx1){
        int fightCount = 0;
        if(Debug){
        }

        for(Critter critter : Liuxx1){
            Liuxx stats = (Liuxx) critter;
            fightCount += stats.fights;
        }

        System.out.println("" + Liuxx1.size() + " total number of Liuxx");
        System.out.println("" + fightCount + " total fights of Liuxx");
    }

    @Override
    public CritterShape viewShape() { return CritterShape.STAR; }

    @Override
    public javafx.scene.paint.Color viewFillColor() { return Color.LIGHTCYAN; }
    @Override
    public javafx.scene.paint.Color viewOutlineColor () { return Color.BLACK; }

    public static Polygon getShape(double x, double y, double radius){
        Polygon star = new Polygon();
        star.getPoints().addAll(
                x + 1, y + 0.8 * radius,
                x + 0.7 * radius, y + 0.8 * radius,
                x + radius, y + 1,
                x + 1.3 * radius, y + 0.8 * radius,
                x + 2 * radius - 1, y + 0.8 * radius,
                x + 1.4 * radius, y + 1.3 * radius,
                x + 1.6 * radius, y + 2 * radius - 1,
                x + radius, y + 1.6 * radius,
                x + 0.4 * radius, y + 2 * radius-1,
                x + 0.6 * radius, y + 1.3 * radius
        );

        return star;
    }

}